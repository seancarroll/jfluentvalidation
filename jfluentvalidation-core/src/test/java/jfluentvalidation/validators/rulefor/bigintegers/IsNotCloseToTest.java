package jfluentvalidation.validators.rulefor.bigintegers;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
import static jfluentvalidation.validators.rulefor.bigintegers.Constants.FIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotCloseToTest {

    @ParameterizedTest
    @CsvSource({
        "1, 3, 1",
        "-1, -3, 1",
        "1, -2, 2",
        "-1, 2, 2"
    })
    void shouldNotReturnFailureWhenDifferenceIsGreaterThanOffset(BigInteger actual, BigInteger expected, BigInteger offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(expected, offset, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 0, 1",
        "-1, 0, 1",
        "-1, 1, 2"
    })
    void shouldNotReturnFailureWhenDifferenceIsEqualToStrictOffset(BigInteger actual, BigInteger expected, BigInteger offsetValue) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(expected, offsetValue, true);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenDifferenceIsLessThanOffset() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(ONE, TEN, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenDifferenceIsLessThanStrictOffset() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(ONE, TEN, true);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, 0",
        "1, 0, 1",
        "-1, 0, 1",
        "-1, -1, 0",
        "-1, 1, 2"
    })
    void shouldReturnFailureWhenDifferenceIsEqualToOffset(BigInteger actual, BigInteger expected, BigInteger offsetValue) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(expected, offsetValue, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotThrowWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(ZERO, ONE, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(null, ONE, false));
    }

    @Test
    void shouldThrowWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(ZERO, null, false));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(ZERO, ONE, false);

        ValidationResult validationResult = validator.validate(t);


        assertEquals("number must not be close to 0 by less than 1.", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldHaveAppropriateErrorMessageForStrictOffset() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigInteger(Target::getNumber).isNotCloseTo(FIVE, TEN, true);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must not be close to 5 by strictly less than 10.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
