package hexlet.code.schemes;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positiveRestriction;
    private Integer minValue;
    private Integer maxValue;
    private boolean rangeRestriction;

    public NumberSchema() {
        super();
        positiveRestriction = false;
        minValue = Integer.MIN_VALUE;
        maxValue = Integer.MAX_VALUE;
        rangeRestriction = false;
    }
    @Override
    public boolean isValid(Integer value) {
        if (value == null && (restriction || positiveRestriction || rangeRestriction)) {
            return false;
        } else if (value == null) {
            return true;
        }

        return isInRange(value) && isPositive(value);
    }

    public void positive() {
        positiveRestriction = true;
    }

    public void range(int num1, int num2) {
        minValue = num1;
        maxValue = num2;
        rangeRestriction = true;
    }

    private boolean isPositive(int num) {
        if (!positiveRestriction) {
            return true;
        } else {
            return num >= 0;
        }
    }

    private boolean isInRange(int num) {
        return num >= minValue && num <= maxValue;
    }
}
