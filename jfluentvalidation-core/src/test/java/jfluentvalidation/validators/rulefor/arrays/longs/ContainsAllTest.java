package jfluentvalidation.validators.rulefor.arrays.longs;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAllTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValues() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(1L, 5L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesInDifferentOrder() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(5L, 1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesMoreThanOnce() {
        Target t = new Target(new long[] {1L, 1L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesEvenIfDuplicated() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(1L, 1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new long[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLongArray(Target::getValue).containsAll((Iterable<Long>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(1L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainExpectedValues() {
        Target t = new Target(new long[] {1L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsAll(5L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
