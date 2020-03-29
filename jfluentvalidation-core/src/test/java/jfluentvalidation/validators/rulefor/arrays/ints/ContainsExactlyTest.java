package jfluentvalidation.validators.rulefor.arrays.ints;

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
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(1, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForIntArray(Target::getValue).containsExactly(new int[] {1, 5}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Integer> iterable = Collections.singletonList(1);
        assertDoesNotThrow(() -> validator.ruleForIntArray(Target::getValue).containsExactly(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new int[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenValuesExactlyButInDifferentOrder() {
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(5, 1);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value have the same elements but not in the same order, at index 0 actual element was 1", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenArraysHaveDifferentSizes() {
        Target t = new Target(new int[] {1, 5, 7});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(1, 5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value elements were not expected [7]", validationResult.getViolations().get(0).getErrorMessage());

    }

    @Test
    void shouldReturnFailureWhenExpectedValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new int[] {1, 5, 7});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForIntArray(Target::getValue).containsExactly((Iterable<Integer>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainGivenValuesExactly() {
        Target t = new Target(new int[] {1, 5, 7});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(1, 5, 9);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value missing the following elements [9] and the following elements were not expected [7]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAllGivenValuesButSizeIsDifferent() {
        Target t = new Target(new int[] {1, 5, 7});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(1, 5, 7, 7);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
