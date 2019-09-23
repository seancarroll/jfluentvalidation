package jfluentvalidation.validators.rulefor.longs;

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
        assertThrows(NullPointerException.class, () -> validator.ruleForLong(Target::getNumber).isStrictlyBetween(null, 5L));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsStrictlyInRange() {
        Target t = new Target(1L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, 5L);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(0L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, 5L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(5L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, 5L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, 5L);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(-1L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, 5L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(6L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isStrictlyBetween(0L, 5L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
