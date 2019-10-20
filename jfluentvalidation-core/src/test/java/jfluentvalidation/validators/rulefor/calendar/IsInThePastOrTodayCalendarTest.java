package jfluentvalidation.validators.rulefor.calendar;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInThePastOrTodayCalendarTest extends AbstractCalendarTest {

    // TODO: test temporal tolerance

    @Test
    void shouldNotReturnFailureWhenActualIsInThePast() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        // DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsToday() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        // DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        // DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(after);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        // DefaultValidator<Target> validator = getValidator();
        validator.ruleForCalendar(Target::getDate).isInThePastOrToday();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
