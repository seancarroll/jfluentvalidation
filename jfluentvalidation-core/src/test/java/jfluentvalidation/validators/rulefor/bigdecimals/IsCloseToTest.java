package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

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
        validator.ruleForBigDecimal(Target::getNumber).isCloseTo(BigDecimal.ZERO, BigDecimal.ONE, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isCloseTo(null, BigDecimal.ONE, false));
    }

    @Test
    void shouldThrowExceptionWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isCloseTo(BigDecimal.ZERO, null, false));
    }

}
