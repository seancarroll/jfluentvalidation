package jfluentvalidation.validators.rulefor.doubles;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsBetweenTest {

    // TODO: add tests for between override inclusive false

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDouble(Target::getNumber).isBetween(null, 5d));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDouble(Target::getNumber).isBetween(0d, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsInRange() {
        Target t = new Target(1d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(0d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(5d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(-1d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(6d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isBetween(0d , 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
