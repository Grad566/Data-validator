package hexlet.code.schemes;

public abstract class BaseSchema<T> {
    protected boolean restriction;

    public BaseSchema() {
        restriction = false;
    }

    public void required() {
        restriction = true;
    }

    public abstract boolean isValid(T value);
}
