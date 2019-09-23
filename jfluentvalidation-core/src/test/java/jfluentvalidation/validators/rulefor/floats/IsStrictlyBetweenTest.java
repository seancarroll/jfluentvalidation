package jfluentvalidation.validators.rulefor.floats;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsStrictlyBetweenTest {

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFloat(Target::getNumber).isStrictlyBetween(null, 5f));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsStrictlyInRange() {
        Target t = new Target(1f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, 5f);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(0f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, 5f);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(5f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, 5f);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, 5f);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(-1f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, 5f);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(6f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isStrictlyBetween(0f, 5f);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
