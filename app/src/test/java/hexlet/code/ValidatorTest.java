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
        var schema = validator.string().minLength(2);
        var actual = schema
                        .isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNullString2() {
        var expected = false;
        var schema = validator.string().required().minLength(2);
        var actual = schema
                .isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorEmptyString() {
        var expected = false;
        var schema = validator.string().contains("asd").required();
        var actual = schema
                        .isValid("");

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorEmptyString2() {
        var expected = true;
        var schema = validator.string().contains("asd");
        var actual = schema
                .isValid("");

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalString() {
        var expected = true;
        var schema = validator.string()
                        .contains("wh")
                        .minLength(2);
        var actual = schema
                        .isValid("what does the fox say");

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalString2() {
        var expected = false;
        var schema = validator.string()
                .contains("wh")
                .minLength(2)
                .contains("asfsdfdsf");
        var actual = schema
                .isValid("what does the fox say");

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalNumber() {
        var expected = true;
        var schema = validator.number()
                .required()
                .range(5, 10)
                .positive();
        var actual = schema
                        .isValid(6);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalNumber2() {
        var expected = false;
        var schema = validator.number()
                .required()
                .range(7, 10)
                .positive();
        var actual = schema
                .isValid(6);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNullNumber() {
        var expected = false;
        var schema = validator.number().required();
        var actual = schema
                        .isValid(null);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNullNumber2() {
        var expected = true;
        var schema = validator.number()
                .range(5, 10)
                .positive();
        var actual = schema
                .isValid(null);

        assertEquals(expected, actual);
    }


    @Test
    public void testValidatorNullMap() {
        var expected = true;
        Map<String, String> data = null;
        var schema = validator.map().sizeof(4);
        var actual = schema
                        .isValid(data);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNullMap2() {
        var expected = false;
        Map<String, String> data = null;
        var schema = validator.map().required().sizeof(3);
        var actual = schema
                .isValid(data);

        assertEquals(expected, actual);
    }

    @Test
    public void testValidatorNormalMap() {
        var expected = true;
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        var schema = validator.map()
                .required()
                .sizeof(2);

        var actual = schema
                        .isValid(data);

        assertEquals(expected, actual);
    }

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
