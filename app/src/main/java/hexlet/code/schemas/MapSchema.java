package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    // добавляет ограничения на null
    @Override
    public MapSchema required() {
        Predicate<Map<?, ?>> notNull = Objects::nonNull;
        validations.add(notNull);
        return this;
    }

    // устанавливает минимальный размер map
    public MapSchema sizeof(int size) {
        Predicate<Map<?, ?>> isTheSameSize = map -> map.size() == size;
        validations.add(isTheSameSize);
        return this;
    }

     // устанавливает проверку валидности вложенных данных
    public MapSchema shape(Map<String, BaseSchema<?>> schema) {
        Predicate<Map<?, ?>> isValidData = map -> {
            for (Map.Entry<String, BaseSchema<?>> entry : schema.entrySet()) {

                if (!map.containsKey(entry.getKey())) {
                    return false;
                } else {

                    if (!entry.getValue().isValid(map.get(entry.getKey()))) {
                        return false;
                    }
                }
            }

            return true;
        };
        validations.add(isValidData);
        return this;
    }

}
