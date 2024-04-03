package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    // активирует ограничения на null и пустую строку
    @Override
    public StringSchema required() {
        Predicate<String> notNull = str -> str != null && !str.isEmpty();
        validations.add(notNull);
        return this;
    }

    // устанавливает минимальную длину строки
    public StringSchema minLength(int length) {
        Predicate<String> isBiggestThenMinLength = str -> str.length() >= length;
        validations.add(isBiggestThenMinLength);
        return this;
    }

    // добавляет ограничение - подстроки, которые должны быть в строке
    public StringSchema contains(String substring) {
        Predicate<String> isContains = str -> str.contains(substring);
        validations.add(isContains);
        return this;
    }

}
