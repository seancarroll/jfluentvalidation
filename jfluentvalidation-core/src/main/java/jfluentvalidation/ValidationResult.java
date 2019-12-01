package jfluentvalidation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ValidationResult {

    private List<ValidationFailure> violations;


    /**
     * Creates a new validationResult
     */
    public ValidationResult() {
        this.violations = new ArrayList<>();
    }

    /**
     * Creates a new ValidationResult from a collection of failures
     *
     * @param violations  List of {@link ValidationFailure}
     */
    public ValidationResult(List<ValidationFailure> violations) {
        this.violations = violations;
    }

    /**
     * Whether validation succeeded
     *
     * @return true if there are no validation failures otherwise false
     */
    public boolean isValid() {
        return violations.isEmpty();
    }

    /**
     *
     * @return
     */
    public List<ValidationFailure> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
            "violations=" + violations +
            '}';
    }

}
