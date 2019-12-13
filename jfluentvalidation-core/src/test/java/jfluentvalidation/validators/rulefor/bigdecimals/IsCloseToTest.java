package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.FIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsCloseToTest {

    @ParameterizedTest
    @CsvSource({
        "1, 1, 1",
        "1, 2, 10",
        "-2, 0, 3",
        "-1, 1, 3",
        "0, 2, 5"
    })
    void shouldNotReturnFailureWhenDifferenceIsLessThanOffset(BigDecimal actual, BigDecimal expected, BigDecimal offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(expected, offset, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 3, 2",
        "3, 1, 2",
        "-2, 0, 2",
        "-1, 1, 2",
        "0, 2, 2"
    })
    void shouldNotReturnFailureWhenDifferenceIsEqualToOffset(BigDecimal actual, BigDecimal expected, BigDecimal offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(expected, offset, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 3, 1",
        "3, 1, 1",
        "-2, 0, 1",
        "-1, 1, 1",
        "0, 2, 1"
    })
    void shouldReturnFailureWhenActualIsNotCloseToExpected(BigDecimal actual, BigDecimal expected, BigDecimal offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(expected, offset, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 1",
        "3, 2, 1",
        "-2, -1, 1",
        "-1, 1, 2",
        "0, 2, 2"
    })
    void shouldReturnFailureWhenDifferenceIsEqualToGivenWithStrictOffset(BigDecimal actual, BigDecimal expected, BigDecimal offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(expected, offset, true);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotThrowWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(ZERO, ONE, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isCloseTo(null, ONE, false));
    }

    @Test
    void shouldThrowExceptionWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isCloseTo(ZERO, null, false));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(FIVE, ONE, false);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must be close to 5 by less than 1.", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldHaveAppropriateErrorMessageForStrictOffset() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(ZERO, ONE, true);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("number must be close to 0 by strictly less than 1.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
