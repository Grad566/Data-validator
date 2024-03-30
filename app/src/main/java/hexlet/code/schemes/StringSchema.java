package hexlet.code.schemes;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema<String> {

    private Integer minValueOfLength;
    private boolean lengthRestriction;
    private List<String> subStrings;
    private boolean subStringsRestriction;

    public StringSchema() {
        super();
        subStrings = new ArrayList<>();
        lengthRestriction = false;
        subStringsRestriction = false;
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

    public void minLength(int length) {
        minValueOfLength = length;
        lengthRestriction = true;
    }

    public void contains(String substring) {
        subStrings.add(substring);
        subStringsRestriction = true;
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
