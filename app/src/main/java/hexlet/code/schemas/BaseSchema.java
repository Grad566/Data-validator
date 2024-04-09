package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> validations;

    public BaseSchema() {
        validations = new HashMap<>();
    }

    // накладывает ограничения на null и частные случаи в зависимости от типа данных
    public abstract BaseSchema<T> required();

    // проверяет валидность данных
    public final boolean isValid(T value) {

        if (validations.containsKey("Required") && isExceptionValue(value)) {
            return validations.get("Required").test(value);
        } else if (isExceptionValue(value)) {
            return true;
        }

        return validations.entrySet().stream()
                .allMatch(predicate -> predicate.getValue().test(value));
    }

    protected abstract boolean isExceptionValue(T value);
}
