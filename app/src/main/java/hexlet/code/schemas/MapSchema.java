package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean restriction;
    private Integer sizeOfMap;
    private boolean sizeRestriction;
    private boolean nestedValidation;
    Map<?, BaseSchema<?>> schemas;

    // в конструкторе устанавливаем все ограничения на false
    public MapSchema() {
        restriction = false;
        sizeRestriction = false;
        nestedValidation = false;
    }

    // активирует ограничения на null
    @Override
    public MapSchema required() {
        restriction = true;
        return this;
    }

    // проверяет валидность данных,
    // проверяет, если нужно, валидность вложенных данных
    // обрабатывает null,
    // проверяет валидность самой map
    @Override
    public boolean isValid(Map<?, ?> value) {

        if (nestedValidation) {
            for (Map.Entry<?, ?> entry : value.entrySet()) {
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
    public MapSchema sizeof(int size) {
        sizeOfMap = size;
        sizeRestriction = true;
        return this;
    }

    // устанавливает проверку валидности вложенных данных
    public MapSchema shape(Map<?, BaseSchema<?>> scheme) {
        nestedValidation = true;
        schemas = new HashMap<>();
        schemas.putAll(scheme);
        return this;
    }

    // проверяет размер map
    private boolean isTheSameSize(Map<?, ?> map) {
        if (!sizeRestriction) {
            return true;
        } else {
            return sizeOfMap == map.size();
        }
    }
}
