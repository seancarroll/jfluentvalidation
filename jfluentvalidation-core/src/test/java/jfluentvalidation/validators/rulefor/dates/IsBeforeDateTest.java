package jfluentvalidation.validators.rulefor.dates;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsBeforeDateTest extends AbstractDateTest {

    IsBeforeDateTest() {
        super(ZonedDateTime.of(
            2019, 8, 7, 9, 0, 0, 0,
            TZ_CHICAGO)
        );
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(after);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(new Date());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(before);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isBefore(reference);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForDate(Target::getDate).isBefore(null));
    }
}
