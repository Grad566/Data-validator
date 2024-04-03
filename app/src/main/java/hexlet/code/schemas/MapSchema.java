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
    public MapSchema shape(Map<?, BaseSchema<?>> schema) {
        Predicate<Map<?, ?>> isValidData = map -> {
            return map.entrySet().stream()
                    .allMatch(entry -> schema.get(entry.getKey()).isValid(entry.getValue()));
        };
        validations.add(isValidData);
        return this;
    }

}
