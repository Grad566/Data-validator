package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void setInput() {
        validator = new Validator();
    }

    @Test
    public void testValidatorNullString() {
        var expected = true;
        var actual = validator.string()
                        .isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorEmptyString() {
        var expected = false;
        var actual = validator.string()
                        .required()
                        .isValid("");

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalString() {
        var expected = true;
        var actual = validator.string()
                        .contains("wh")
                        .minLength(2)
                        .isValid("what does the fox say");

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalNumber() {
        var expected = true;
        var actual = validator.number()
                        .required()
                        .range(5, 10)
                        .positive()
                        .isValid(6);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNullNumber() {
        var expected = true;
        var actual = validator.number()
                        .isValid(null);

        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testValidatorNullMap() {
        var expected = true;
        Map<String, String> data = null;
        var actual = validator.map()
                        .isValid(data);

        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testValidatorNormalMap() {
        var expected = true;
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");

        var actual = validator.map()
                        .required()
                        .sizeof(2)
                        .isValid(data);

        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testValidatorNestedMap() {
        var expected = true;
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        var actual = schema.isValid(human);

        assertEquals(expected, actual);
    }

}
