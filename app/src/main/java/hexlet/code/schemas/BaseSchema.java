package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> validations;

    public BaseSchema() {
        validations = new HashMap<>();
    }

    // накладывает ограничения на null и частные случаи в зависимости от типа данных
    public abstract BaseSchema<T> required();

    // проверяет валидность данных
    public final boolean isValid(T value) {

        return validations.entrySet().stream()
                .allMatch(predicate -> predicate.getValue().test(value));

    }

    protected final void addValidation(String name, Predicate<T> predicate) {
        validations.put(name, predicate);
    }

}
