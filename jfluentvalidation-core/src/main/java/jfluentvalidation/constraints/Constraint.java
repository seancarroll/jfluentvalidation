package jfluentvalidation.constraints;

public interface Constraint<T> {

    boolean isValid(T instance);

}
