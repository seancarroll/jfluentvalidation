package jfluentvalidation.validators.rulefor.longs;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsGreaterThanTest {

    @Test
    void shouldNotReturnFailureWhenActualIsGreaterThanGiven() {
        Target t = new Target(11L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isGreaterThan(10L);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGiven() {
        Target t = new Target(10L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isGreaterThan(10L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLessThanGiven() {
        Target t = new Target(9L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isGreaterThan(10L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isGreaterThan(10L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
