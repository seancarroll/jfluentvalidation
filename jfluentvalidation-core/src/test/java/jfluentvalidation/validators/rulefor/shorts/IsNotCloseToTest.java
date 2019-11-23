package jfluentvalidation.validators.rulefor.shorts;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static jfluentvalidation.validators.rulefor.shorts.Constants.FIVE;
import static jfluentvalidation.validators.rulefor.shorts.Constants.ONE;
import static jfluentvalidation.validators.rulefor.shorts.Constants.ZERO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotCloseToTest {

    @Test
    void shouldThrowExceptionWhenExpectedIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForShort(Target::getNumber).isNotCloseTo(null, ONE, false));
    }

    @Test
    void shouldThrowExceptionWhenOffsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForShort(Target::getNumber).isNotCloseTo(ZERO, null, false));
    }

    @Test
    void shouldThrowExceptionWhenStartIsAfterEnd() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(IllegalArgumentException.class, () -> validator.ruleForShort(Target::getNumber).isNotBetween(FIVE, ONE));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 3, 1",
        "-1, -3, 1",
        "1, -2, 2",
        "-1, 2, 2"
    })
    void shouldNotReturnFailureWhenDifferenceIsGreaterThanOffset(short actual, short expected, short offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isNotCloseTo(expected, offset, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 0, 1",
        "1, 2, 1",
        "-1, 0, 1",
        "1, -1, 2",
        "-1, 1, 2"
    })
    void shouldNotReturnFailureWhenDifferenceIsEqualToStrictOffset(short actual, short expected, short offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isNotCloseTo(expected, offset, true);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 10",
        "1, 2, 2",
        "1, 0, 2",
        "0, 1, 2"
    })
    void shouldReturnFailureWhenActualIsTooCloseToExpected(short actual, short expected, short offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isNotCloseTo(expected, offset, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 10",
        "1, 0, 2",
        "0, 1, 2"
    })
    void shouldReturnFailureWhenActualIsTooCloseToExpectedValueWithStrictOffset(short actual, short expected, short offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isNotCloseTo(expected, offset, true);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, 0",
        "1, 0, 1",
        "1, 2, 1"
    })
    void shouldReturnFailureWhenDifferenceIsEqualToOffset(short actual, short expected, short offset) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isNotCloseTo(expected, offset, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotThrowWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isNotCloseTo(ZERO, ONE, false);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

}
