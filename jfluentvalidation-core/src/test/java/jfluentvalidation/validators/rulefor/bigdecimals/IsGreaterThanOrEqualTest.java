package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.TEN;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.FIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsGreaterThanOrEqualTest {

    @Test
    void shouldNotReturnFailureWhenActualIsGreaterThanGiven() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThanOrEqualTo(FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGiven() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThanOrEqualTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThanOrEqualTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLessThanGiven() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThanOrEqualTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThanOrEqualTo(TEN);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must be greater than or equal to 10.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
