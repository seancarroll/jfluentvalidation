package jfluentvalidation.validators.rulefor.arrays.bytes;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasLengthBetweenTest {

    private final byte one = 1;

    @Test
    void shouldNotReturnFailureWhenActualLengthIsGreaterThanMinAndLessThanMax() {
        Target t = new Target(new byte[] {one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasLengthBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasLengthBetween(0, 5);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsLessThanMin() {
        Target t = new Target(new byte[] {one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasLengthBetween(10, 20);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthEqualsMinAndInclusiveStartIsFalse() {
        Target t = new Target(new byte[] {one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasLengthBetween(1, 5, false, true);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsGreaterThanMax() {
        Target t = new Target(new byte[] {one, one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasLengthBetween(0, 1);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthEqualsMaxAndInclusiveEndIsFalse() {
        Target t = new Target(new byte[] {one, one});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).hasLengthBetween(0, 2, true, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenMinIsGreaterThanMax() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(IllegalArgumentException.class, () -> validator.ruleForByteArray(Target::getValue).hasLengthBetween(6, 5));
    }

}
