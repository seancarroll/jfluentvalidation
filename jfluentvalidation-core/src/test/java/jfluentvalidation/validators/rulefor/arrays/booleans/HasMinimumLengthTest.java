package jfluentvalidation.validators.rulefor.arrays.booleans;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasMinimumLengthTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToMin() {
        Target t = new Target(new boolean[] {true});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).hasMinimumLength(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsGreaterThanMin() {
        Target t = new Target(new boolean[] {true, false});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).hasMinimumLength(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).hasMinimumLength(3);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsLessThanMinimum() {
        Target t = new Target(new boolean[] {true});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).hasMinimumLength(2);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
