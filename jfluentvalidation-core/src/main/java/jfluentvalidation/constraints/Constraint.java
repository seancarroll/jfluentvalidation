package jfluentvalidation.constraints;

/**
 * Defines the logic to validate a given constraint for a given object type {@code T}
 * @param <T> the target type supported by an implementation
 */
public interface Constraint<T> {

    /**
     * Implements the validation logic.
     *
     * @param value object to validate
     * @return {@code false} if {@code value} does not pass the constraint
     */
    boolean isValid(T value);

}
