package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    // устанавливаем ограничения на null
    @Override
    public NumberSchema required() {
        isRequire = true;
        return this;
    }

    // устанавливает ограничения - число не может быть отрицательным
    public NumberSchema positive() {
        Predicate<Integer> isPositive = num -> num >= 0;
        validations.add(isPositive);
        return this;
    }

    // устанавливает допустимый диапазон
    public NumberSchema range(int num1, int num2) {
        Predicate<Integer> isInRange = num -> num >= num1 && num <= num2;
        validations.add(isInRange);
        return this;
    }

}
