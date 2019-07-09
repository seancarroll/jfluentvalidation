package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalDateTimeTest {

    private static final LocalDateTime ACTUAL = LocalDateTime.of(2019, 06, 15, 0, 0, 0);
    private static final LocalDateTime BEFORE = LocalDateTime.of(2019, 06, 14, 0, 0, 0);
    private static final LocalDateTime AFTER = LocalDateTime.of(2019, 06, 16, 0, 0, 0);

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(LocalDateTime.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDateTime(Target::getDateTime).isAfter(null));
    }

}
