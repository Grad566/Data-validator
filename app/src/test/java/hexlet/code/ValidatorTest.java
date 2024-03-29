package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    private static Validator validator;
    private static String emptyString;
    private static String nullString;
    private static String str;

    @BeforeAll
    public static void setInput() {
        emptyString = "";
        nullString = null;
        str = "what does the fox say";
        validator = new Validator();
    }

    @Test
    public void validatorNullString() {
        var expected = true;
        var schema = validator.string();
        var actual = schema.isValid(nullString);

        assertEquals(expected, actual);
    }

    @Test
    public void validatorEmptyString() {
        var expected = false;
        var schema = validator.string();

        schema.required();
        var actual = schema.isValid(emptyString);

        assertEquals(expected, actual);
    }

    @Test
    public void validatorNormalString() {
        var expected = true;
        var schema = validator.string();

        schema.contains("wh");
        schema.minLength(2);

        var actual = schema.isValid(str);

        assertEquals(expected, actual);
    }
}