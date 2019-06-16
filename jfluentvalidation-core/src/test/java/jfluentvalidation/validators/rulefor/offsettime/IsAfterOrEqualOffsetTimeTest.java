package jfluentvalidation.validators.rulefor.offsettime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterOrEqualOffsetTimeTest {

    private static final OffsetTime ACTUAL = OffsetTime.of(2, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetTime BEFORE = OffsetTime.of(1, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetTime AFTER = OffsetTime.of(3, 0, 0, 0, ZoneOffset.UTC);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetTime(Target::getTime).isAfterOrEqual(OffsetTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetTime(Target::getTime).isAfterOrEqual(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetTime(Target::getTime).isAfterOrEqual(ACTUAL);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetTime(Target::getTime).isAfterOrEqual(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForOffsetTime(Target::getTime).isAfterOrEqual(null));
    }

}
