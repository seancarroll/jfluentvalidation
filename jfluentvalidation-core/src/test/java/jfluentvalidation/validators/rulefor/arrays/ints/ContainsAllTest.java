package jfluentvalidation.validators.rulefor.arrays.ints;

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
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(1, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesInDifferentOrder() {
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(5, 1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesMoreThanOnce() {
        Target t = new Target(new int[] {1, 1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesEvenIfDuplicated() {
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(1, 1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new int[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForIntArray(Target::getValue).containsAll((Iterable<Integer>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainExpectedValues() {
        Target t = new Target(new int[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsAll(5);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
