package jfluentvalidation.validators.rulefor.zoneddatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInThePastTest {

    private static final ZonedDateTime PAST = ZonedDateTime.now().minusDays(1);
    private static final ZonedDateTime FUTURE = ZonedDateTime.now().plusDays(1);

    // TODO: test for same zonedDateTime. need to implement clock.

    @Test
    void shouldNotReturnFailureWhenActualIsInThePast() {
        Target t = new Target(PAST);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isInThePast();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isInThePast();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(FUTURE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isInThePast();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
