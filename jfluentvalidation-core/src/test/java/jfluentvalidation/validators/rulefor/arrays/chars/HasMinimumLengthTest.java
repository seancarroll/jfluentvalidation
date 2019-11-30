package jfluentvalidation.validators.rulefor.arrays.chars;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasMinimumLengthTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToMin() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasMinimumLength(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsGreaterThanMin() {
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasMinimumLength(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasMinimumLength(3);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsLessThanMinimum() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasMinimumLength(2);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
