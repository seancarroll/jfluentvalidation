package jfluentvalidation.validators.rulefor.object;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotEqualToTest {

    @Test
    void shouldReturnFailureWhenActualAsPrimitiveEqualsGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualReferenceEqualsGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(o);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualOverriddenEqualsMatchesGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(new IdOverriddenEquals("some-id"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAsPrimitiveDoesNotEqualGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(7);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualReferenceDoesNotEqualGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(new Object());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualOverriddenEqualsDoesNotMatchGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(new IdOverriddenEquals("other-id"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

}
