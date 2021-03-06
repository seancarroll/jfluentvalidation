package jfluentvalidation.validators.rulefor.arrays.shorts;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasMaximumLengthTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToMaximum() {
        Target t = new Target(new short[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasMaximumLength(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsLessThanMaximum() {
        Target t = new Target(new short[] {1, 2});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasMaximumLength(2);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasMaximumLength(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsGreaterThanMaximum() {
        Target t = new Target(new short[] {1, 2});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).hasMaximumLength(1);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
