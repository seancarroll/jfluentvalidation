package jfluentvalidation.validators.rulefor.integer;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotPositiveTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotPositive() {
        Target t = new Target(-1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotPositive();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsZero() {
        Target t = new Target(0);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotPositive();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotPositive();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsPositive() {
        Target t = new Target(1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isNotPositive();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
