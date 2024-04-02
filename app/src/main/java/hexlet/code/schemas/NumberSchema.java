package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean restriction;
    private boolean positiveRestriction;
    private Integer minValue;
    private Integer maxValue;
    private boolean rangeRestriction;

    // в конструкторе устанавливаем все ограничения на false
    public NumberSchema() {
        restriction = false;
        positiveRestriction = false;
        minValue = Integer.MIN_VALUE;
        maxValue = Integer.MAX_VALUE;
        rangeRestriction = false;
    }

    // активирует ограничения на null
    @Override
    public NumberSchema required() {
        restriction = true;
        return this;
    }

    // сначала обрабатывает случаи с null,
    // затем проверяет валидность числа
    @Override
    public boolean isValid(Integer value) {
        if (value == null && (restriction || positiveRestriction || rangeRestriction)) {
            return false;
        } else if (value == null) {
            return true;
        }

        return isInRange(value) && isPositive(value);
    }

    // устанавливает ограничения - число не может быть отрицательным
    public NumberSchema positive() {
        positiveRestriction = true;
        return this;
    }

    // устанавливает допустимый диапазон
    public NumberSchema range(int num1, int num2) {
        minValue = num1;
        maxValue = num2;
        rangeRestriction = true;
        return this;
    }

    // проверяет положительность числа
    private boolean isPositive(int num) {
        if (!positiveRestriction) {
            return true;
        } else {
            return num >= 0;
        }
    }

    // проверяет вхождение числа в диапазон
    private boolean isInRange(int num) {
        return num >= minValue && num <= maxValue;
    }
}
