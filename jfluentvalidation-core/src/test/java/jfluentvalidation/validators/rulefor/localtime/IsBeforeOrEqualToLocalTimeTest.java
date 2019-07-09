package jfluentvalidation.validators.rulefor.localtime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsBeforeOrEqualToLocalTimeTest {

    private static final LocalTime ACTUAL = LocalTime.of(2, 0, 0, 0);
    private static final LocalTime BEFORE = LocalTime.of(1, 0, 0, 0);
    private static final LocalTime AFTER = LocalTime.of(3, 0, 0, 0);

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isBeforeOrEqualTo(ACTUAL);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isBeforeOrEqualTo(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isBeforeOrEqualTo(LocalTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isBeforeOrEqualTo(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(Target::getTime).isBeforeOrEqualTo(null));
    }
}
