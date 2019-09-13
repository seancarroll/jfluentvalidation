package jfluentvalidation.validators;

import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.rules.RuleSet;

import java.util.List;

// TODO: add generic T (<T>) to ValidationContext?
// TODO: should we return a ValidationResult instead?
// TODO: I wish I didnt have to expose the context versions but currently requires as its used by IncludeRule
public interface Validator<T> {

    // which of these to make default?

    List<ValidationFailure> validate(T entity);

    List<ValidationFailure> validate(T entity, String ruleSet);

    List<ValidationFailure> validate(T entity, List<String> ruleSet);

    List<ValidationFailure> validate(ValidationContext context);

    List<ValidationFailure> validate(ValidationContext context, List<String> ruleSet);

    void validateAndThrow(T entity);

    void validateAndThrow(T entity, String ruleSet);

    void validateAndThrow(T entity, List<String> ruleSet);

    default void validateAndThrow(ValidationContext validationContext) {
        validateAndThrow(validationContext, RuleSet.DEFAULT_LIST);
    }

    /**
     * Performs validation and then throws an exception if validation fails.
     *
     * @param validationContext
     * @param ruleSet a ruleset when need to validate against.
     */
    default void validateAndThrow(ValidationContext validationContext, List<String> ruleSet) {
        List<ValidationFailure> failures = validate(validationContext, ruleSet);
        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }
    }
}
