package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInThePastOffsetDateTimeTest extends AbstractOffsetDateTime {

    IsInThePastOffsetDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 8, 0, 0, 0,
            TZ_CHICAGO));
    }

    // TODO: test temporal tolerance
    // TODO: test for same  need to implement clock.

    @Test
    void shouldNotReturnFailureWhenActualIsInThePast() {
        Target t = new Target(BEFORE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInThePast();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInThePast();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(AFTER);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInThePast();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}