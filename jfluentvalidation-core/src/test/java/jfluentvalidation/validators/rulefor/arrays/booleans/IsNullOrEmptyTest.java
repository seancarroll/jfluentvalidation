package jfluentvalidation.validators.rulefor.arrays.booleans;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNullOrEmptyTest {

    @Test
    void shouldNotReturnFailureWhenStringIsEmpty() {
        Target t = new Target(new boolean[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).isNullOrEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).isNullOrEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotNullOrEmpty() {
        Target t = new Target(new boolean[] {true});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBooleanArray(Target::getValue).isNullOrEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
