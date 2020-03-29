package jfluentvalidation.validators.rulefor.arrays.longs;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAnyTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValues() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForLongArray(Target::getValue).containsAny(new long[]{1L, 5L}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Long> iterable = Collections.singletonList(1L);
        assertDoesNotThrow(() -> validator.ruleForLongArray(Target::getValue).containsAny(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenValues() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(1L, 5L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesInDifferentOrder() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(5L, 1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesMoreThanOnce() {
        Target t = new Target(new long[] {1L, 1L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsJustOneOfTheGivenValues() {
        Target t = new Target(new long[] {1L, 1L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(1L, 5L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesEvenIfDuplicated() {
        Target t = new Target(new long[] {1L, 1L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(1L, 1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new long[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenGivenValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainAnyOfTheGivenValues() {
        Target t = new Target(new long[] {1L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(5L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLongArray(Target::getValue).containsAny((Iterable<Long>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAny(2L, 3L);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must contain at least one of the following: [2, 3] but none were found.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
