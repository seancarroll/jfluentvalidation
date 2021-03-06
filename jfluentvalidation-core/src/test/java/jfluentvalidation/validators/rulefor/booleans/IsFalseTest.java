package jfluentvalidation.validators.rulefor.booleans;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsFalseTest {

    @Test
    void shouldNotReturnFailureWhenActualIsFalse() {
        Target t = new Target(false);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isFalse();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isFalse();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsTrue() {
        Target t = new Target(true);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isFalse();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
