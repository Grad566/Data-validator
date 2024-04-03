package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> validations;

    public BaseSchema() {
        validations = new ArrayList<>();
    }
    // накладывает ограничения на null и частные случаи в зависимости от типа данных
    public abstract BaseSchema<T> required();

    // проверяет валидность данных
    public boolean isValid(T value) {

        if (value == null && validations.isEmpty()) {
            return true;
        } else if (value == null) {
            return true;
        }

        return validations.stream()
                .allMatch(predicate -> predicate.test(value));
    }
}
