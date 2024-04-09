package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    // добавляет ограничения на null
    @Override
    public MapSchema required() {
        validations.put("Required", Objects::nonNull);
        return this;
    }

    // устанавливает минимальный размер map
    public MapSchema sizeof(int size) {
        validations.put("sizeOf", map -> map.size() == size);
        return this;
    }


    // проверяет валидность вложенных данных
    @SuppressWarnings("unchecked")
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        validations.put("isValidData", map -> schemas.entrySet()
                .stream()
                .allMatch(element -> {
                    var value = map.get(element.getKey());
                    var schema = element.getValue();
                    return schema.isValid((T) value);
                }));
        return this;
    }
}
