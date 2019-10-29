package jfluentvalidation.validators.rulefor.object;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualToTest {

    @Test
    void shouldNotReturnFailureWhenActualAsPrimitiveEqualsGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(5);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualReferenceEqualsGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(o);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(5);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualOverriddenEqualsMatchesGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(new IdOverriddenEquals("some-id"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualAsPrimitiveDoesNotEqualGiven() {
        Target t = new Target(5);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(7);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualReferenceDoesNotEqualGiven() {
        Object o = new Object();
        Target t = new Target(o);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(new Object());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualOverriddenEqualsDoesNotMatcheGiven() {
        Target t = new Target(new IdOverriddenEquals("some-id"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObject(Target::getId).isEqualTo(new IdOverriddenEquals("other-id"));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
