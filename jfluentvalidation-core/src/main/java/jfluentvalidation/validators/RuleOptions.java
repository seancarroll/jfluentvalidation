package jfluentvalidation.validators;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.Duration;

public class RuleOptions {

    // private Supplier<ClockProvider> clockProvider = () -> ValidatorOptions.CLOCK_PROVIDER;
    private ClockProvider clockProvider =  ValidatorOptions.CLOCK_PROVIDER;
    private Duration temporalValidationTolerance = ValidatorOptions.TEMPORAL_VALIDATION_TOLERANCE;
    private FailureMode failureMode = FailureMode.CONTINUE;

    public ClockProvider getClockProvider() {
        return clockProvider;
    }

    public void setClockProvider(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    public Duration getTemporalValidationTolerance() {
        return temporalValidationTolerance;
    }

    public void setTemporalValidationTolerance(Duration temporalValidationTolerance) {
        this.temporalValidationTolerance = temporalValidationTolerance;
    }

    public FailureMode getFailureMode() {
        return failureMode;
    }

    public void setFailureMode(FailureMode failureMode) {
        this.failureMode = failureMode;
    }

    // TODO: will need to be able to negate tolerance
    public Clock getClockReference() {
        return Clock.offset(clockProvider.getClock(), temporalValidationTolerance);
    }
}
