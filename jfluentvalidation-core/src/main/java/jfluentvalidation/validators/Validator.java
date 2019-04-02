package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;

import java.util.List;

// TODO: should we return a ValidationResult instead?
public interface Validator<T> {

    List<ValidationFailure> validate(T entity);

    List<ValidationFailure> validate(ValidationContext context);

    // make default?
    List<ValidationFailure> validate(ValidationContext context, List<String> ruleSet);
}
