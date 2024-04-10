package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    // активирует ограничения на null и пустую строку
    @Override
    public StringSchema required() {
        addValidation("Required", value -> value != null && !value.isEmpty());
        return this;
    }

    // устанавливает минимальную длину строки
    public StringSchema minLength(int length) {
        addValidation("minLength", str -> str == null
                || str.length() >= length);
        return this;
    }

    // добавляет ограничение - подстроки, которые должны быть в строке
    public StringSchema contains(String substring) {
        addValidation("isContains", str -> str == null
                || str.contains(substring));
        return this;
    }

}
