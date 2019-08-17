package jfluentvalidation.validators;

// Hibernate has a boolean
// FluentValidation has CascadeMode: Continue, StopOnFirstFailure
public enum FailureMode {
    CONTINUE,
    STOP_ON_FIRST_FAILURE;
}
