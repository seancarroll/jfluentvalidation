package jfluentvalidation.validators;

import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.rules.RuleSet;

import java.util.List;

// TODO: should we return a ValidationResult instead?
public interface Validator<T> {

    // which of these to make default?

    List<ValidationFailure> validate(T entity);

    List<ValidationFailure> validate(T entity, List<String> ruleSet);

    List<ValidationFailure> validate(ValidationContext context);

    List<ValidationFailure> validate(ValidationContext context, List<String> ruleSet);

    default void validateAndThrow(ValidationContext validationContext) {
        validateAndThrow(validationContext, RuleSet.DEFAULT_LIST);
    }

    /**
     * Performs validation and then throws an exception if validation fails.
     * @param validationContext
     * @param ruleSet  a ruleset when need to validate against.
     */
    default void validateAndThrow(ValidationContext validationContext, List<String> ruleSet) {
        List<ValidationFailure> failures = validate(validationContext, ruleSet);
        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }
    }

}
