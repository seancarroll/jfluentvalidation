package jfluentvalidation.validators.rulefor.object;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualToTest {

    @Test
    void shouldNotReturnFailureWhenActualAsPrimitiveEqualsGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualReferenceEqualsGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(o);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualOverriddenEqualsMatchesGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(new IdOverriddenEquals("some-id"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualAsPrimitiveDoesNotEqualGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(7);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualReferenceDoesNotEqualGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(new Object());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualOverriddenEqualsDoesNotMatcheGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(new IdOverriddenEquals("other-id"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
