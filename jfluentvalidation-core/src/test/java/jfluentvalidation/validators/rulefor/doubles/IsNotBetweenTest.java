package jfluentvalidation.validators.rulefor.doubles;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotBetweenTest {

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDouble(Target::getNumber).isNotBetween(null, 5d));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDouble(Target::getNumber).isNotBetween(0d, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(-1d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(6d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(0d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsEqualToStartAndStartIsExclusive() {
        Target t = new Target(0d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d, false, true);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(5d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsEqualToEndAndEndIsExclusive() {
        Target t = new Target(5d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d, true, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInRange() {
        Target t = new Target(1d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotBetween(0d, 5d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
