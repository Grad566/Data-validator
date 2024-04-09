package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {
    private static Validator validator;

    @BeforeAll
    public static void setInput() {
        validator = new Validator();
    }

    @Test
    public void testNumberSchemaRequired() {
        var schema = validator.number()
                .range(5, 10)
                .positive();

        assertTrue(schema.isValid(null));
    }

    @Test
    public void testNumberSchemaRequired2() {
        var schema = validator.number()
                .range(5, 10)
                .positive()
                .required();

        assertFalse(schema.isValid(null));
    }

    @Test
    public void testNumberSchemaPositive() {
        var schema = validator.number()
                .positive();

        assertFalse(schema.isValid(-6));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testNumberSchemaRange() {
        var schema = validator.number()
                .required()
                .range(5, 10);

        assertTrue(schema.isValid(6));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testNumberSchemaAllRestriction() {
        var schema = validator.number()
                .required()
                .range(5, 10)
                .positive();

        assertFalse(schema.isValid(2));
        assertFalse(schema.isValid(null));
    }

}
