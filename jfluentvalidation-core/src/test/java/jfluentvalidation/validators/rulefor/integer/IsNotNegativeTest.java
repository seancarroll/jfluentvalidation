package jfluentvalidation.validators.rulefor.integer;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotNegativeTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotNegative() {
        Target t = new Target(1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotNegative();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsZero() {
        Target t = new Target(0);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotNegative();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNegative() {
        Target t = new Target(-1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotNegative();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotNegative();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}