package jfluentvalidation.validators.rulefor.doubles;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsGreaterThanOrEqualTest {

    @Test
    void shouldNotReturnFailureWhenActualIsGreaterThanGiven() {
        Target t = new Target(11d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isGreaterThanOrEqualTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGiven() {
        Target t = new Target(10d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isGreaterThanOrEqualTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLessThanGiven() {
        Target t = new Target(9d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isGreaterThanOrEqualTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isGreaterThanOrEqualTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}