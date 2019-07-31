package jfluentvalidation.validators.rulefor.calendar;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsBeforeOrEqualToCalendarTest extends AbstractCalendarTest {

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(Calendar.getInstance());

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForCalendar(Target::getDate).isBeforeOrEqualTo(null));
    }

}
