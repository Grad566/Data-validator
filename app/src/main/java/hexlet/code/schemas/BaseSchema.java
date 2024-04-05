package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> validations;
    protected Predicate<T> notNull = Objects::nonNull;

    public BaseSchema() {
        validations = new ArrayList<>();
    }
    // накладывает ограничения на null и частные случаи в зависимости от типа данных
    public BaseSchema<T> required() {
        validations.add(notNull);
        return this;
    }

    // проверяет валидность данных
    public boolean isValid(T value) {

        if (value == null && validations.contains(notNull)) {
            return false;
        } else if (value == null) {
            return true;
        }

        return validations.stream()
                .allMatch(predicate -> predicate.test(value));
    }
}
