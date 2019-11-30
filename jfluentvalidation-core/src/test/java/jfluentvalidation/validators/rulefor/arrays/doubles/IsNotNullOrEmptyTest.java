package jfluentvalidation.validators.rulefor.arrays.doubles;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotNullOrEmptyTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotEmpty() {
        Target t = new Target(new double[]{1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).isNotNullOrEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).isNotNullOrEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsEmpty() {
        Target t = new Target(new double[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).isNotNullOrEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
