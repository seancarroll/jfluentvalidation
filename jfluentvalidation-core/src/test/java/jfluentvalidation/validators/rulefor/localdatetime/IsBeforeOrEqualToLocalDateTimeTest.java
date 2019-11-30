package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsBeforeOrEqualToLocalDateTimeTest extends AbstractLocalDateTimeTest {

    IsBeforeOrEqualToLocalDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 0, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(reference);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(after);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(LocalDateTime.now());

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(before);

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDateTime(Target::getDateTime).isBeforeOrEqualTo(null));
    }
}
