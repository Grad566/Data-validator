package hexlet.code.schemes;

public abstract class BaseSchema<T> {
    public abstract BaseSchema<T> required();

    public abstract boolean isValid(T value);
}
