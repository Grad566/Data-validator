package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    // активирует ограничения на null и пустую строку
    @Override
    public StringSchema required() {
        validations.put("Required", value -> value != null && !value.isEmpty());
        return this;
    }

    // устанавливает минимальную длину строки
    public StringSchema minLength(int length) {
        validations.put("minLength", str -> str.length() >= length);
        return this;
    }

    // добавляет ограничение - подстроки, которые должны быть в строке
    public StringSchema contains(String substring) {
        validations.put("isContains", str -> str.contains(substring));
        return this;
    }

    @Override
    protected boolean isExceptionValue(String value) {
        return value == null || value.isEmpty();
    }
}
