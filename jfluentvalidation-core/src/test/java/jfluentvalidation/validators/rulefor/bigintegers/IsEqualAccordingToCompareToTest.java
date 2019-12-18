package jfluentvalidation.validators.rulefor.bigintegers;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualAccordingToCompareToTest {

    @Test
    void shouldNotReturnFailureWhenObjectsAreEqualViaCompareTo() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isEqualAccordingToCompareTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isEqualAccordingToCompareTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenObjectsAreNotEqualViaCompareTo() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isEqualAccordingToCompareTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isEqualAccordingToCompareTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must be equal to 10.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
