package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInTheFutureOrTodayOffsetDateTimeTest extends AbstractOffsetDateTime {

    IsInTheFutureOrTodayOffsetDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 0, 0, 0, 0,
            TZ_CHICAGO));
    }

    // TODO: test temporal tolerance

    @Test
    void shouldNotReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(after);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFutureOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsThePresent() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFutureOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFutureOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInThePast() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFutureOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}