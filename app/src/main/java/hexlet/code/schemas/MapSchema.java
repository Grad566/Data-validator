package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    // добавляет ограничения на null
    @Override
    public MapSchema required() {
        isRequire = true;
        return this;
    }

    // устанавливает минимальный размер map
    public MapSchema sizeof(int size) {
        Predicate<Map<?, ?>> isTheSameSize = map -> map.size() == size;
        validations.put("sizeOf", isTheSameSize);
        return this;
    }


    // проверяет валидность вложенных данных
    @SuppressWarnings("unchecked")
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<?, ?>> isValidData = map -> schemas.entrySet()
                .stream()
                .allMatch(element -> {
                    var value = map.get(element.getKey());
                    var schema = element.getValue();
                    return schema.isValid((T) value);
                });
        validations.put("isValidData", isValidData);
        return this;
    }
}
