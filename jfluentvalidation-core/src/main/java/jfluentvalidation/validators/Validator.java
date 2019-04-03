package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;

import java.util.List;

// TODO: should we return a ValidationResult instead?
public interface Validator<T> {

    // which of these to make default?

    List<ValidationFailure> validate(T entity);

    List<ValidationFailure> validate(T entity, List<String> ruleSet);

    List<ValidationFailure> validate(ValidationContext context);

    List<ValidationFailure> validate(ValidationContext context, List<String> ruleSet);

}
