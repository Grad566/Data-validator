package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setInput() {
        validator = new Validator();
    }

    @Test
    public void validatorNullString() {
        var expected = true;
        var schema = validator.string();
        var actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void validatorEmptyString() {
        var expected = false;
        var schema = validator.string();

        schema.required();
        var actual = schema.isValid("");

        assertEquals(expected, actual);
    }

    @Test
    public void validatorNormalString() {
        var expected = true;
        var schema = validator.string();

        schema.contains("wh");
        schema.minLength(2);

        var actual = schema.isValid("what does the fox say");

        assertEquals(expected, actual);
    }

    @Test
    public void validatorNormalNumber() {
        var expected = true;
        var schema = validator.number();

        schema.required();
        schema.range(5, 10);
        schema.positive();

        var actual = schema.isValid(5);

        assertEquals(expected, actual);
    }

    @Test
    public void validatorNullNumber() {
        var expected = true;
        var schema = validator.number();

        var actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void validatorNullMap() {
        var expected = true;
        var schema = validator.map();

        var actual = schema.isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void validatorNormalMap() {
        var expected = true;
        var schema = validator.map();

        schema.required();
        schema.sizeof(2);

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");

        var actual = schema.isValid(data);

        assertEquals(expected, actual);
    }

}
