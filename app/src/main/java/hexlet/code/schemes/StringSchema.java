package hexlet.code.schemes;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema<String> {
    private boolean restriction;
    private Integer minValueOfLength;
    private boolean lengthRestriction;
    private List<String> subStrings;
    private boolean subStringsRestriction;

    // в конструкторе устанавливаем все ограничения на false
    public StringSchema() {
        restriction = false;
        subStrings = new ArrayList<>();
        lengthRestriction = false;
        subStringsRestriction = false;
    }

    // активирует ограничения на null и пустую строку
    @Override
    public StringSchema required() {
        restriction = true;
        return this;
    }

    // сначала обрабатывает случаи с null,
    // затем проверяет валидность строки
    @Override
    public boolean isValid(String str) {

        if ((str == null || str.isEmpty())
                && (restriction || lengthRestriction || subStringsRestriction)) {
            return false;
        } else if (str == null) {
            return true;
        }

        return isContains(str) && isTheSameLength(str);
    }

    // устанавливает минимальную длину строки
    public StringSchema minLength(int length) {
        minValueOfLength = length;
        lengthRestriction = true;
        return this;
    }

    // добавляет ограничение - подстроки, которые должны быть в строке
    public StringSchema contains(String substring) {
        subStrings.add(substring);
        subStringsRestriction = true;
        return this;
    }

    // проверяет содержит ли строка подстроки
    private boolean isContains(String str) {

        for (var sub : subStrings) {
            if (!str.contains(sub)) {
                return false;
            }
        }

        return true;
    }

    // проверяет длину строки
    private boolean isTheSameLength(String str) {
        if (!lengthRestriction) {
            return true;
        } else {
            return str.length() > minValueOfLength;
        }
    }
}
