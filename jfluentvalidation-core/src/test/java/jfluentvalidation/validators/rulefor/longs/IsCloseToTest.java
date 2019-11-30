package jfluentvalidation.validators.rulefor.longs;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void shouldNotReturnFailureWhenDifferenceIsLessThanOffset(long actual, long expected, long offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isCloseTo(expected, offset, false);

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
    void shouldNotReturnFailureWhenDifferenceIsEqualToOffset(long actual, long expected, long offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isCloseTo(expected, offset, false);

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
    void shouldReturnFailureWhenActualIsNotCloseToExpected(long actual, long expected, long offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isCloseTo(expected, offset, false);

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
    void shouldReturnFailureWhenDifferenceIsEqualToGivenWithStrictOffset(long actual, long expected, long offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isCloseTo(expected, offset, true);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotThrowWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isCloseTo(0L, 1L, false);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLong(Target::getNumber).isCloseTo(null, 1L, false));

    }

    @Test
    void shouldThrowExceptionWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLong(Target::getNumber).isCloseTo(0L, null, false));
    }

}
