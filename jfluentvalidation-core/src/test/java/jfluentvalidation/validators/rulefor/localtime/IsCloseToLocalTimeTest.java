package jfluentvalidation.validators.rulefor.localtime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsCloseToLocalTimeTest  extends AbstractLocalTimeTest {

    IsCloseToLocalTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 8, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldThrowExceptionWhenExpectedIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(Target::getTime).isCloseTo(null, 1, ChronoUnit.MINUTES, false));
    }

    @Test
    void shouldThrowExceptionWhenOffsetUnitIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(Target::getTime).isCloseTo(LocalTime.now(), 1, null, false));
    }

    @Test
    void shouldThrowExceptionWhenOffsetIsNotApplicable() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(UnsupportedTemporalTypeException.class, () -> validator.ruleForLocalTime(Target::getTime).isCloseTo(LocalTime.now(), 1, ChronoUnit.WEEKS, false));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalTime(Target::getTime).isCloseTo(LocalTime.now(), 1, ChronoUnit.MINUTES, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenWithinOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalTime(Target::getTime).isCloseTo(before, 2, ChronoUnit.HOURS, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenEqualsOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalTime(Target::getTime).isCloseTo(before, 1, ChronoUnit.HOURS, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenOutsideOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalTime(Target::getTime).isCloseTo(before, 1, ChronoUnit.MINUTES, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenEqualsStrictOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalTime(Target::getTime).isCloseTo(before, 1, ChronoUnit.HOURS, true);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
