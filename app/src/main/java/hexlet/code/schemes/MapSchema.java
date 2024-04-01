package hexlet.code.schemes;

import java.util.HashMap;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    private boolean restriction;
    private Integer sizeOfMap;
    private boolean sizeRestriction;
    private boolean nestedValidation;
    Map<K, BaseSchema<V>> schemas;

    // в конструкторе устанавливаем все ограничения на false
    public MapSchema() {
        restriction = false;
        sizeRestriction = false;
        nestedValidation = false;
    }

    // активирует ограничения на null
    @Override
    public MapSchema<K, V> required() {
        restriction = true;
        return this;
    }

    // проверяет валидность данных,
    // проверяет, если нужно, валидность вложенных данных
    // обрабатывает null,
    // проверяет валидность самой map
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

    // устанавливает минимальный размер map
    public MapSchema<K, V> sizeof(int size) {
        sizeOfMap = size;
        sizeRestriction = true;
        return this;
    }

    // устанавливает проверку валидности вложенных данных
    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> scheme) {
        nestedValidation = true;
        schemas = new HashMap<>();
        schemas.putAll(scheme);
        return this;
    }

    // проверяет размер map
    private boolean isTheSameSize(Map<K, V> map) {
        if (!sizeRestriction) {
            return true;
        } else {
            return sizeOfMap == map.size();
        }
    }
}
