package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    // устанавливает размер map
    public MapSchema sizeof(int size) {
        addValidation("sizeOf", map -> map == null || map.size() == size);
        return this;
    }


    // проверяет валидность вложенных данных
    @SuppressWarnings("unchecked")
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation("isValidData", map -> schemas.entrySet()
                .stream()
                .allMatch(element -> {
                    var value = map.get(element.getKey());
                    var schema = element.getValue();
                    return schema.isValid((T) value);
                }));
        return this;
    }

}
