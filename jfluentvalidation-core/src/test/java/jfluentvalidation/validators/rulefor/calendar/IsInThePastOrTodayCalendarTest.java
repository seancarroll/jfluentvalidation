package jfluentvalidation.validators.rulefor.calendar;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInThePastOrTodayCalendarTest extends AbstractCalendarTest {

    IsInThePastOrTodayCalendarTest() {
        super(ZonedDateTime.of(
            2019, 8, 7, 9, 0, 0, 0,
            TZ_CHICAGO)
        );
    }

    // TODO: test temporal tolerance

    @Test
    void shouldNotReturnFailureWhenActualIsInThePast() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsToday() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(after);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    // TODO: add back
//    @Test
//    void shouldHaveAppropriateErrorMessage() {
//        Target t = new Target(after);
//
//        DefaultValidator<Target> validator = getValidator();
//        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();
//
//        ValidationResult validationResult = validator.validate(t);
//
//        assertEquals("date must be a past date or today.", validationResult.getViolations().get(0).getErrorMessage());
//    }
}
