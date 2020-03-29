package jfluentvalidation.validators.rulefor.arrays.doubles;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsExactlyTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesExactly() {
        Target t = new Target(new double[]{1d, 5d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(1d, 5d);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForDoubleArray(Target::getValue).containsExactly(new double[]{1d, 5d}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Double> iterable = Collections.singletonList(1d);
        assertDoesNotThrow(() -> validator.ruleForDoubleArray(Target::getValue).containsExactly(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new double[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenValuesExactlyButInDifferentOrder() {
        Target t = new Target(new double[]{1d, 5d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(5d, 1d);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value have the same elements but not in the same order, at index 0 actual element was 1.0", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenArraysHaveDifferentSizes() {
        Target t = new Target(new double[] {1d, 5d, 10d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(1d, 5d);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value elements were not expected [10.0]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenExpectedValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new double[]{1d, 5d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDoubleArray(Target::getValue).containsExactly((Iterable<Double>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainGivenValuesExactly() {
        Target t = new Target(new double[] {1d, 5d, 7d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(1d, 5d, 9d);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value missing the following elements [9.0] and the following elements were not expected [7.0]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAllGivenValuesButSizeIsDifferent() {
        Target t = new Target(new double[] {1d, 5d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsExactly(1d, 5d, 5d);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
