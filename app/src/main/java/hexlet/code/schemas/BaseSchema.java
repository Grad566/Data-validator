package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    // накладывает ограничения на null и частные случаи в зависимости от типа данных
    public abstract BaseSchema<T> required();

    // проверяет валидность данных
    public abstract boolean isValid(T value);
}
