package jfluentvalidation.validators;

// TODO: do we need this? Can we delete
public class RunnableValidator<T> extends DefaultValidator<T> {

    public RunnableValidator(Class<T> clazz, Runnable runnable) {
        super(clazz);
        runnable.run();
    }
}
