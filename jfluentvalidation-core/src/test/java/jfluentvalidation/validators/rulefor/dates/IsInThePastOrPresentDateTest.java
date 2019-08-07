package jfluentvalidation.validators.rulefor.dates;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInThePastOrPresentTest {

    private static final Date PAST = Date.from(Instant.now().minus(Duration.ofDays(1)));
    private static final Date FUTURE = Date.from(Instant.now().plus(Duration.ofDays(1)));

    // TODO: test for same  need to implement clock.

    @Test
    void shouldNotReturnFailureWhenActualIsInThePast() {
        Target t = new Target(PAST);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDate(Target::getDate).isInThePastOrPresent();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDate(Target::getDate).isInThePastOrPresent();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(FUTURE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDate(Target::getDate).isInThePastOrPresent();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
