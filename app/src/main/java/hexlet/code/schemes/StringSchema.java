package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {
    private boolean restriction;
    private Integer lengthRestriction;
    private List<String> subStrings;

    public StringSchema() {
        restriction = false;
        subStrings = new ArrayList<>();
    }

    public void required() {
        restriction = true;
    }

    public void minLength(int length) {
        lengthRestriction = length;
    }

    public void contains(String substring) {
        subStrings.add(substring);
    }

    public boolean isValid(String str) {

        if (restriction && (str == null || str.isEmpty())) {
            return false;
        }

        if (str == null && (!subStrings.isEmpty() || lengthRestriction != null)) {
            return false;
        } else if (str == null) {
            return true;
        }

        return isContains(str) && isTheSameLength(str);
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
        if (lengthRestriction == null) {
            return true;
        } else {
            return str.length() > lengthRestriction;
        }
    }
}
