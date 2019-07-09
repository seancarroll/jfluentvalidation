package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HasLengthBetweenTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsGreaterThanMinAndLessThanMax() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).hasLengthBetween(0, 10);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).hasLengthBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsLessThanMin() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).hasLengthBetween(10, 20);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthEqualsMinAndInclusiveStartIsFalse() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).hasLengthBetween(5, 10, false, true);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsGreaterThanMax() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).hasLengthBetween(0, 3);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthEqualsMaxAndInclusiveEndIsFalse() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).hasLengthBetween(0, 5, true, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenMinIsGreaterThanMax() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(IllegalArgumentException.class, () -> validator.ruleForString(Target::getValue).hasLengthBetween(6, 5));
    }
}
