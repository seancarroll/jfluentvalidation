package jfluentvalidation.validators.rulefor.dates;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.*;

class IsBeforeDateTest extends AbstractDateTest {

    IsBeforeDateTest() {
        super(ZonedDateTime.of(
            2019, 8, 7, 9, 0, 0, 0,
            TZ_CHICAGO)
        );
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target t = new Target(REFERENCE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(new Date());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target t = new Target(REFERENCE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(REFERENCE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(REFERENCE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForDate(Target::getDate).isBefore(null));
    }
}
