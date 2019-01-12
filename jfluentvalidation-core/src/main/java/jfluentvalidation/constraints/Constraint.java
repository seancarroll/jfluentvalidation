package jfluentvalidation.constraints;

public interface Constraint<T> {

    boolean IsValid(T instance);

}
