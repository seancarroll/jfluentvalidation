package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.FIVE;
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
    void shouldNotReturnFailureWhenDifferenceIsGreaterThanOffset(BigDecimal actual, BigDecimal expected, BigDecimal offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(expected, offset, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 0.0, 1.0",
        "-1.0, 0.0, 1.0",
        "-1.0, 1.0, 2.0"
    })
    void shouldNotReturnFailureWhenDifferenceIsEqualToStrictOffset(BigDecimal actual, BigDecimal expected, BigDecimal offsetValue) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(expected, offsetValue, true);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenDifferenceIsLessThanOffset() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(ONE, TEN, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenDifferenceIsLessThanStrictOffset() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(ONE, TEN, true);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 1.0, 0.0",
        "1.0, 0.0, 1.0",
        "-1.0, 0.0, 1.0",
        "-1.0, -1.0, 0.0",
        "-1.0, 1.0, 2.0"
    })
    void shouldReturnFailureWhenDifferenceIsEqualToOffset(BigDecimal actual, BigDecimal expected, BigDecimal offsetValue) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(expected, offsetValue, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotThrowWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(ZERO, ONE, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(null, ONE, false));
    }

    @Test
    void shouldThrowWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isNotCloseTo(ZERO, null, false));
    }
}
