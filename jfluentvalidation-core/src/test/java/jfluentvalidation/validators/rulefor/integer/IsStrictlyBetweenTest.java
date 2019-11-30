package jfluentvalidation.validators.rulefor.integer;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsStrictlyBetweenTest {

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInteger(Target::getNumber).isStrictlyBetween(null, 5));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsStrictlyInRange() {
        Target t = new Target(1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(0);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(-1);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(6);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInteger(Target::getNumber).isStrictlyBetween(0, 5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
