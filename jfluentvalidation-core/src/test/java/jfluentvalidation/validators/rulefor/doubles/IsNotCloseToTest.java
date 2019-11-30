package jfluentvalidation.validators.rulefor.doubles;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void shouldNotReturnFailureWhenDifferenceIsGreaterThanOffset(double actual, double expected, double offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotCloseTo(expected, offset, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @CsvSource({
        "1.0, 0.0, 1.0",
        "-1.0, 0.0, 1.0",
        "-1.0, 1.0, 2.0"
    })
    void shouldNotReturnFailureWhenDifferenceIsEqualToStrictOffset(double actual, double expected, double offsetValue) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotCloseTo(expected, offsetValue, true);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenDifferenceIsLessThanOffset() {
        Target t = new Target(5d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotCloseTo(1d, 10d, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenDifferenceIsLessThanStrictOffset() {
        Target t = new Target(5d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotCloseTo(1d, 10d, true);

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
    void shouldReturnFailureWhenDifferenceIsEqualToOffset(double actual, double expected, double offsetValue) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotCloseTo(expected, offsetValue, false);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotThrowWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isNotCloseTo(0d, 1d, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDouble(Target::getNumber).isNotCloseTo(null, 1d, false));
    }

    @Test
    void shouldThrowWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDouble(Target::getNumber).isNotCloseTo(0d, null, false));
    }
}
