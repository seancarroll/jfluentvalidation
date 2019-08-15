package jfluentvalidation.validators;

import javax.validation.ClockProvider;
import java.time.Duration;

public class ValidatorFactory {

    private ClockProvider clockProvider;
    private FailureMode failureMode;
    private Duration temporalValidationTolerance;

    public ValidatorFactory withClockProvider(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
        return this;
    }

    public ValidatorFactory withFailureMode(FailureMode failureMode) {
        this.failureMode = failureMode;
        return this;
    }

    public ValidatorFactory withTemporalValidationTolerance(Duration temporalValidationTolerance) {
        this.temporalValidationTolerance = temporalValidationTolerance;
        return this;
    }

    public <T> DefaultValidator<T> create(Class<T> type) {
        DefaultValidator<T> validator = new DefaultValidator<>(type);
        if (clockProvider != null) {
            validator.setClockProvider(clockProvider);
        }
        if (failureMode != null) {
            validator.setFailureMode(failureMode);
        }

        return validator;
    }
}
