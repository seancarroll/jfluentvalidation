package jfluentvalidation.validators.rulefor.calendar;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Calendar;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotCloseToTest extends AbstractCalendarTest {

    IsNotCloseToTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 8, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldThrowExceptionWhenExpectedIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForCalendar(Target::getDate).isNotCloseTo(null, 100, false));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isNotCloseTo(Calendar.getInstance(), 1, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }


    @Test
    void shouldNotReturnFailureWhenOutsideOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isNotCloseTo(before, 1_000, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenEqualsStrictOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isNotCloseTo(before, 86_400_000, true);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenWithinOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isNotCloseTo(before, 90_000_000, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenEqualsOffset() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isNotCloseTo(before,   86_400_000, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
