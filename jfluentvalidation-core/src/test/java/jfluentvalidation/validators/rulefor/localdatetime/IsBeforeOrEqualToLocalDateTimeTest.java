package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.*;

class IsBeforeOrEqualToLocalDateTimeTest extends AbstractLocalDateTimeTest {

    IsBeforeOrEqualToLocalDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 0, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(REFERENCE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(REFERENCE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target p = new Target(REFERENCE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(LocalDateTime.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target p = new Target(REFERENCE);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(null));
    }
}
