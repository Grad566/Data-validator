package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    // активирует ограничения на null и пустую строку
    @Override
    public StringSchema required() {
        isRequire = true;
        return this;
    }

    // устанавливает минимальную длину строки
    public StringSchema minLength(int length) {
        Predicate<String> isBiggestThenMinLength = str -> str.length() >= length;
        validations.put("minLength", isBiggestThenMinLength);
        return this;
    }

    // добавляет ограничение - подстроки, которые должны быть в строке
    public StringSchema contains(String substring) {
        Predicate<String> isContains = str -> str.contains(substring);
        validations.put("isContains", isContains);
        return this;
    }

}
