package jfluentvalidation.validators.rulefor.arrays.floats;

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
        Target t = new Target(new float[]{1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(1f, 5f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForFloatArray(Target::getValue).containsExactly(new float[]{1f, 5f}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Float> iterable = Collections.singletonList(1f);
        assertDoesNotThrow(() -> validator.ruleForFloatArray(Target::getValue).containsExactly(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new float[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenValuesExactlyButInDifferentOrder() {
        Target t = new Target(new float[]{1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(5f, 1f);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value have the same elements but not in the same order, at index 0 actual element was 1.0", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenArraysHaveDifferentSizes() {
        Target t = new Target(new float[] {1f, 5f, 10f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(1f, 5f);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value elements were not expected [10.0]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenExpectedValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new float[]{1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFloatArray(Target::getValue).containsExactly((Iterable<Float>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainGivenValuesExactly() {
        Target t = new Target(new float[] {1f, 5f, 7f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(1f, 5f, 9f);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value missing the following elements [9.0] and the following elements were not expected [7.0]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAllGivenValuesButSizeIsDifferent() {
        Target t = new Target(new float[] {1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsExactly(1f, 5f, 5f);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
