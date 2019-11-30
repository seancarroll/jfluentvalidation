package jfluentvalidation.validators.rulefor.object;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotNullTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotNull() {
        Target t = new Target(new Object());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotNull();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotNull();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
