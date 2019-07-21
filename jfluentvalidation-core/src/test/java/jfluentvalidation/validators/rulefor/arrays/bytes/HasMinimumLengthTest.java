package jfluentvalidation.validators.rulefor.arrays.bytes;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasMinimumLengthTest {

    private final byte one = 1;

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToMin() {
        Target t = new Target(new byte[] {one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasMinimumLength(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsGreaterThanMin() {
        Target t = new Target(new byte[] {one, one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasMinimumLength(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasMinimumLength(3);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsLessThanMinimum() {
        Target t = new Target(new byte[] {one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasMinimumLength(2);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
