package jfluentvalidation;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ValidationFailure> failures;

    public ValidationException(List<ValidationFailure> failures) {
        this.failures = failures;
    }

    // TODO: return immutable list
    public List<ValidationFailure> getFailures() {
        return failures;
    }
}
