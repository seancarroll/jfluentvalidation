package jfluentvalidation.validators.rulefor.arrays.shorts;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotEmptyTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotEmpty() {
        Target t = new Target(new short[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).isNotEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).isNotEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsEmpty() {
        Target t = new Target(new short[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShortArray(Target::getValue).isNotEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
