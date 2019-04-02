package jfluentvalidation.validators;

public class RunnableValidator<T> extends DefaultValidator<T> {

    public RunnableValidator(Class<T> clazz, Runnable runnable) {
        super(clazz);
        runnable.run();
    }
}
