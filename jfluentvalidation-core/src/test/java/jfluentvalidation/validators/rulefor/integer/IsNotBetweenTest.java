package jfluentvalidation.validators.rulefor.integer;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class IsNotBetweenTest {

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInteger(Target::getNumber).isNotBetween(null, 5));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInteger(Target::getNumber).isNotBetween(0, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(-1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(6);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(0);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInRange() {
        Target t = new Target(1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
