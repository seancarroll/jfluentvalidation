package jfluentvalidation.validators.rulefor.floats;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotEqualAccordingToCompareToTest {

    @Test
    void shouldNotReturnFailureWhenObjectsAreNotEqualViaCompareTo() {
        Target t = new Target(0f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isNotEqualAccordingToCompareTo(10f);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isNotEqualAccordingToCompareTo(10f);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenObjectsAreEqualViaCompareTo() {
        Target t = new Target(10f);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloat(Target::getNumber).isNotEqualAccordingToCompareTo(10f);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
