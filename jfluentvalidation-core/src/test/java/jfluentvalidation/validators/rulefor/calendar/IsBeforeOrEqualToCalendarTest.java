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

class IsBeforeOrEqualToCalendarTest extends AbstractCalendarTest {

    IsBeforeOrEqualToCalendarTest() {
        super(ZonedDateTime.of(
            2019, 8, 7, 9, 0, 0, 0,
            TZ_CHICAGO)
        );
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(reference);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(after);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(Calendar.getInstance());

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(before);

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(null));
    }

}
