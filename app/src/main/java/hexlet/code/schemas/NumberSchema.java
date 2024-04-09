package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    // устанавливаем ограничения на null
    @Override
    public NumberSchema required() {
        validations.put("Required", Objects::nonNull);
        return this;
    }

    // устанавливает ограничения - число не может быть отрицательным
    public NumberSchema positive() {
        validations.put("isPositive", num -> num > 0);
        return this;
    }

    // устанавливает допустимый диапазон
    public NumberSchema range(int num1, int num2) {
        validations.put("isInRange", num -> num >= num1 && num <= num2);
        return this;
    }

    @Override
    protected boolean isExceptionValue(Integer value) {
        return value == null;
    }

}
