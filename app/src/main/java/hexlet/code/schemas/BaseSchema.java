package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> validations;
    protected boolean isRequire;

    public BaseSchema() {
        validations = new HashMap<>();
        isRequire = false;
    }
    // накладывает ограничения на null и частные случаи в зависимости от типа данных
    public  abstract BaseSchema<T> required();

    // проверяет валидность данных
    public boolean isValid(T value) {

        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            return !isRequire;
        }

        return validations.entrySet().stream()
                .allMatch(predicate -> predicate.getValue().test(value));
    }
}
