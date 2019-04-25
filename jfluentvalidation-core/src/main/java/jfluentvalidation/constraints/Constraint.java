package jfluentvalidation.constraints;

import jfluentvalidation.validators.RuleContext;

/**
 * Defines the logic to validate a given constraint for a given object type {@code T}
 * @param <T> the target type supported by an implementation
 * @param <P>
 */
public interface Constraint<T, P> {

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
//    /**
//     * Implements the validation logic.
//     *
//     * @param value object to validate
//     * @return {@code false} if {@code value} does not pass the constraint
//     */
//    boolean isValid(T value);


    // TODO: should it be ValidationContext<? super T, P> context?
//    /**
//     * Implements the validation logic.
//     *
//     * @param context The validation context
//     * @return {@code false} if {@code value} does not pass the constraint
//     */
//    boolean isValid(RuleContext<T, P> context);

    /**
     * Implements the validation logic.
     *
     * @param context The validation context
     * @return {@code false} if {@code value} does not pass the constraint
     */
    boolean isValid(RuleContext<T, P> context);


//    // TODO: including T value in the method signature for now to make it easier to refactor logic to validate method
//    // however I think the plan is to have it be included in the validation context somehow
//    /**
//     * Performs validation
//     * @param value object to validate
//     * @param context The validation context
//     * @return A collection of validation failures
//     */
//    List<ValidationFailure> validate(T value, ValidationContext context);

}
