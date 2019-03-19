package jfluentvalidation.constraints;

/**
 * Defines the logic to validate a given constraint for a given object type {@code T}
 * @param <T> the target type supported by an implementation
 */
public interface Constraint<T> {

//    From Hibernate Validator
//    /**
//     * Implements the validation logic.
//     * The state of {@code value} must not be altered.
//     * <p>
//     * This method can be accessed concurrently, thread-safety must be ensured
//     * by the implementation.
//     *
//     * @param value object to validate
//     * @param context context in which the constraint is evaluated
//     *
//     * @return {@code false} if {@code value} does not pass the constraint
//     */
//    boolean isValid(T value, ConstraintValidatorContext context);

    // TODO: should we pass in a context like hibernate validator?
    // Possible things to contain
    // 1. constraint message template
    // 2. ClockProvider


    // TODO: still not happy with the parameter name
    /**
     * Implements the validation logic.
     *
     * @param value object to validate
     * @return {@code false} if {@code value} does not pass the constraint
     */
    boolean isValid(T value);

}
