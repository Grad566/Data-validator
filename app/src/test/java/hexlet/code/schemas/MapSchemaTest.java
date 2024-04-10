package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MapSchemaTest {
    private static Validator validator;

    @BeforeAll
    public static void setInput() {
        validator = new Validator();
    }

    @Test
    public void testMapSchemaRequired() {
        Map<String, String> data = null;
        var schema = validator.map();

        assertTrue(schema.isValid(data));

        schema.required();

        assertFalse(schema.isValid(data));
    }

    @Test
    public void testMapSchemaSizeOf() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        var schema = validator.map()
                .sizeof(2);

        assertTrue(schema.isValid(data));

        data.put("key3", "value3");

        assertFalse(schema.isValid(data));
        assertTrue(schema.isValid(null));
    }

    @Test
    public void testMapSchemaAllRestrictions() {
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas).required().sizeof(2);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertTrue(schema.isValid(human));
        assertFalse(schema.isValid(null));
    }

    @Test
    public void testMapSchemaNestedValidation() {
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas).required();

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertTrue(schema.isValid(human));
        assertFalse(schema.isValid(null));

        human.put("lastName", null);

        assertFalse(schema.isValid(human));
    }

}
