package jfluentvalidation.validators.rulefor.doubles;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualAccordingToCompareToTest {


    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isEqualAccordingToCompareTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenObjectsAreEqualViaCompareTo() {
        Target t = new Target(10d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isEqualAccordingToCompareTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenObjectsAreNotEqualViaCompareTo() {
        Target t = new Target(0d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isEqualAccordingToCompareTo(10d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
