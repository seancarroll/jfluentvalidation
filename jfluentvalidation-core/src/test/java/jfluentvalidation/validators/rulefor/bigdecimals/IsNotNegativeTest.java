package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.NEGATIVE_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotNegativeTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotNegative() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotNegative();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsZero() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotNegative();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotNegative();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNegative() {
        Target t = new Target(NEGATIVE_ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotNegative();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(NEGATIVE_ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotNegative();

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must be greater than or equal to 0.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
