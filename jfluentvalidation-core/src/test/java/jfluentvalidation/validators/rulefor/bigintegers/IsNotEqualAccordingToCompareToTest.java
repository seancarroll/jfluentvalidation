package jfluentvalidation.validators.rulefor.bigintegers;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotEqualAccordingToCompareToTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotEqualAccordingToCompareTo(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenObjectsAreNotEqualViaCompareTo() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotEqualAccordingToCompareTo(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenObjectsAreEqualViaCompareTo() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotEqualAccordingToCompareTo(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}