package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {
    private static Validator validator;

    @BeforeAll
    public static void setInput() {
        validator = new Validator();
    }

    @Test
    public void testStringSchemaRequiredNull() {
        var schema = validator.string()
                .minLength(2);

        assertTrue(schema.isValid(null));
    }

    @Test
    public void testStringSchemaRequiredNull2() {
        var schema = validator.string()
                .required()
                .minLength(2);

        assertFalse(schema.isValid(null));
    }

    @Test
    public void testStringSchemaRequiredEmptyString() {
        var schema = validator.string()
                .contains("asd")
                .required();

        assertFalse(schema.isValid(""));
    }

    @Test
    public void testStringSchemaRequiredEmptyString2() {
        var schema = validator.string()
                .contains("asd");

        assertTrue(schema.isValid(""));
    }

    @Test
    public void testStringSchemaContains() {
        var schema = validator.string()
                .contains("wh");

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testStringSchemaMinLength() {
        var schema = validator.string()
                .minLength(30)
                .required();

        assertFalse(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testStringSchemaAllRestrictions() {
        var schema = validator.string()
                .minLength(2)
                .contains("does")
                .required();

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
    }

}
