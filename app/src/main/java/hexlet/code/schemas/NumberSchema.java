package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    // устанавливаем ограничения на null
    @Override
    public NumberSchema required() {
        addValidation("Required", Objects::nonNull);
        return this;
    }

    // устанавливает ограничения - число не может быть отрицательным
    public NumberSchema positive() {
        addValidation("isPositive", num -> num == null || num > 0);
        return this;
    }

    // устанавливает допустимый диапазон
    public NumberSchema range(int num1, int num2) {
        addValidation("isInRange", num -> num == null || (num >= num1 && num <= num2));
        return this;
    }

}
