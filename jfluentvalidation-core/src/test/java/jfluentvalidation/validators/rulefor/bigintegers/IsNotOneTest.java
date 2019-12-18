package jfluentvalidation.validators.rulefor.bigintegers;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotOneTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotOne() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotOne();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotOne();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsOne() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotOne();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotOne();

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must not be equal to 1.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
