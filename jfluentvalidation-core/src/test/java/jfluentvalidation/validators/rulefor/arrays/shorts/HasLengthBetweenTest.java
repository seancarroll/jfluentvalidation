package jfluentvalidation.validators.rulefor.arrays.shorts;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasLengthBetweenTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsGreaterThanMinAndLessThanMax() {
        Target t = new Target(new short[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasLengthBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasLengthBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsLessThanMin() {
        Target t = new Target(new short[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasLengthBetween(10, 20);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthEqualsMinAndInclusiveStartIsFalse() {
        Target t = new Target(new short[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasLengthBetween(1, 5, false, true);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsGreaterThanMax() {
        Target t = new Target(new short[] {1, 2});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasLengthBetween(0, 1);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthEqualsMaxAndInclusiveEndIsFalse() {
        Target t = new Target(new short[] {1, 2});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasLengthBetween(0, 2, true, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenMinIsGreaterThanMax() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(IllegalArgumentException.class, () -> validator.ruleForShortArray(Target::getValue).hasLengthBetween(6, 5));
    }
}
