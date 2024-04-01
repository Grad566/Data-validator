package hexlet.code.schemes;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private boolean restriction;
    private Integer sizeOfMap;
    private boolean sizeRestriction;
    private boolean nestedValidation;
    Map<K, BaseSchema<V>> schemas;

    public MapSchema() {
        restriction = false;
        sizeRestriction = false;
        nestedValidation = false;
    }

    @Override
    public MapSchema<K, V> required() {
        restriction = true;
        return this;
    }

    @Override
    public boolean isValid(Map<K, V> value) {

        if (nestedValidation) {
            for (Map.Entry<K, V> entry : value.entrySet()) {
                if (!schemas.get(entry.getKey()).isValid(entry.getValue())) {
                    return false;
                }
            }

            return true;
        }

        if (value == null && (restriction || sizeRestriction)) {
            return false;
        } else if (value == null) {
            return true;
        }

        return  isTheSameSize(value);
    }

    public MapSchema<K, V> sizeof(int size) {
        sizeOfMap = size;
        sizeRestriction = true;
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> scheme) {
        nestedValidation = true;
        schemas = new HashMap<>();
        schemas.putAll(scheme);
        return this;
    }

    private boolean isTheSameSize(Map<K, V> map) {
        if (!sizeRestriction) {
            return true;
        } else {
            return sizeOfMap == map.size();
        }
    }
}
