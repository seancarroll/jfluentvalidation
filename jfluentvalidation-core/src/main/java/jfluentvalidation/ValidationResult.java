package jfluentvalidation;

import jfluentvalidation.constraints.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private List<ConstraintViolation> violations;


    /**
     * Creates a new validationResult
     */
    public ValidationResult() {
        this.violations = new ArrayList<>();
    }

    /**
     * Creates a new ValidationResult from a collection of failures
     *
     * @param violations  List of <see cref="ValidationFailure"/> which is later available through <see cref="Errors"/>. This list get's copied.
     */
    public ValidationResult(List<ConstraintViolation> violations) {
        this.violations = violations;
    }

    public boolean isValid() {
        return violations.isEmpty();
    }

    public List<ConstraintViolation> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
            "violations=" + violations +
            '}';
    }

}
