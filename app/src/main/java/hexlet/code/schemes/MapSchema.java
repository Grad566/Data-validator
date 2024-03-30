package hexlet.code.schemes;

import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private Integer sizeOfMap;
    private boolean sizeRestriction;

    public MapSchema() {
        super();
        sizeRestriction = false;
    }

    @Override
    public boolean isValid(Map<K, V> value) {

        if (value == null && (restriction || sizeRestriction)) {
            return false;
        } else if (value == null) {
            return true;
        }

        return  isTheSameSize(value);
    }

    public void sizeof(int size) {
        sizeOfMap = size;
        sizeRestriction = true;
    }

    private boolean isTheSameSize(Map<K, V> map) {
        if (!sizeRestriction) {
            return true;
        } else {
            return sizeOfMap == map.size();
        }
    }
}
