package jfluentvalidation.validators.rulefor.dates;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.*;

class IsBeforeOrEqualToDateTest extends AbstractDateTest {

    IsBeforeOrEqualToDateTest() {
        super(ZonedDateTime.of(
            2019, 8, 7, 9, 0, 0, 0,
            TZ_CHICAGO)
        );
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBeforeOrEqualTo(reference);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBeforeOrEqualTo(after);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBeforeOrEqualTo(new Date());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBeforeOrEqualTo(before);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForDate(Target::getDate).isBeforeOrEqualTo(null));
    }
}
