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
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testStringSchemaContains() {
        var schema = validator.string()
                .contains("wh");

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("Don't know"));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testStringSchemaMinLength() {
        var schema = validator.string()
                .minLength(2);

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("wh"));
        assertFalse(schema.isValid("1"));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testStringSchemaAllRestrictions() {
        var schema = validator.string()
                .minLength(5)
                .contains("does")
                .required();

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("what do you say"));
        assertFalse(schema.isValid(null));
    }

}
