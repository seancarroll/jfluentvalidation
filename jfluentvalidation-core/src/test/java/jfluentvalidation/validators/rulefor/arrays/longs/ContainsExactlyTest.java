package jfluentvalidation.validators.rulefor.arrays.longs;

import com.google.common.collect.Sets;
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
        Target t = new Target(new long[]{1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(1L, 5L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForLongArray(Target::getValue).containsExactly(new long[]{1L, 5L}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForLongArray(Target::getValue).containsExactly(Sets.newHashSet(1L, 5L)));
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new long[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenValuesExactlyButInDifferentOrder() {
        Target t = new Target(new long[]{1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(5L, 1L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value have the same elements but not in the same order, at index 0 actual element was 1", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenArraysHaveDifferentSizes() {
        Target t = new Target(new long[] {1L, 5L, 10L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(1L, 5L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value elements were not expected [10]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenExpectedValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new long[]{1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLongArray(Target::getValue).containsExactly((Iterable<Long>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainGivenValuesExactly() {
        Target t = new Target(new long[] {1L, 5L, 7L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(1L, 5L, 9L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value missing the following elements [9] and the following elements were not expected [7]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAllGivenValuesButSizeIsDifferent() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsExactly(1L, 5L, 5L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
