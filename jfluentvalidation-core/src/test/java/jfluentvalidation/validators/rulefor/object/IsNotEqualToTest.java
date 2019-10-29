package jfluentvalidation.validators.rulefor.object;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotEqualToTest {

    @Test
    void shouldReturnFailureWhenActualAsPrimitiveEqualsGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(5);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualReferenceEqualsGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(o);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualOverriddenEqualsMatchesGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(new IdOverriddenEquals("some-id"));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAsPrimitiveDoesNotEqualGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(7);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualReferenceDoesNotEqualGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(new Object());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualOverriddenEqualsDoesNotMatchGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isNotEqualTo(new IdOverriddenEquals("other-id"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

}
