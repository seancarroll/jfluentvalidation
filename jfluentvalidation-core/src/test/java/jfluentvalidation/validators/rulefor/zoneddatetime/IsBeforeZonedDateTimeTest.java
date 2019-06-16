package jfluentvalidation.validators.rulefor.zoneddatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsBeforeZonedDateTimeTest {

    private static final ZonedDateTime ACTUAL = ZonedDateTime.of(2019, 6, 15, 0, 0, 0, 0, ZoneOffset.UTC);
    private static final ZonedDateTime BEFORE = ZonedDateTime.of(2019, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC);
    private static final ZonedDateTime AFTER = ZonedDateTime.of(2019, 6, 16, 0, 0, 0, 0, ZoneOffset.UTC);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isBefore(ZonedDateTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isBefore(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDateBasedOnTimeZone() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isBefore(BEFORE.withZoneSameInstant(ZoneId.of("America/Chicago")));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isBefore(ACTUAL);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isBefore(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDateBasedOnTimeZone() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForZonedDateTime(Target::getDateTime).isBefore(AFTER.withZoneSameInstant(ZoneId.of("Europe/London")));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForZonedDateTime(Target::getDateTime).isBefore(null));
    }
}
