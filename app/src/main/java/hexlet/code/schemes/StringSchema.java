package hexlet.code.schemes;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema<String> {
    private boolean restriction;
    private Integer minValueOfLength;
    private boolean lengthRestriction;
    private List<String> subStrings;
    private boolean subStringsRestriction;

    public StringSchema() {
        restriction = false;
        subStrings = new ArrayList<>();
        lengthRestriction = false;
        subStringsRestriction = false;
    }

    @Override
    public StringSchema required() {
        restriction = true;
        return this;
    }

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

    public StringSchema minLength(int length) {
        minValueOfLength = length;
        lengthRestriction = true;
        return this;
    }

    public StringSchema contains(String substring) {
        subStrings.add(substring);
        subStringsRestriction = true;
        return this;
    }

    private boolean isContains(String str) {

        for (var sub : subStrings) {
            if (!str.contains(sub)) {
                return false;
            }
        }

        return true;
    }

    private boolean isTheSameLength(String str) {
        if (!lengthRestriction) {
            return true;
        } else {
            return str.length() > minValueOfLength;
        }
    }
}
