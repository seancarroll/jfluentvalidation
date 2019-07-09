package jfluentvalidation.validators.rulefor.localtime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalTimeTest {

    private static final LocalTime ACTUAL = LocalTime.of(2, 0, 0, 0);
    private static final LocalTime BEFORE = LocalTime.of(1, 0, 0, 0);
    private static final LocalTime AFTER = LocalTime.of(3, 0, 0, 0);

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isAfter(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isAfter(LocalTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isAfter(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalTime(Target::getTime).isAfter(ACTUAL);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(Target::getTime).isAfter(null));
    }
}
