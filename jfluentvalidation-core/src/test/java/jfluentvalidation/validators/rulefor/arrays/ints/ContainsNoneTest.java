package jfluentvalidation.validators.rulefor.arrays.ints;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsNoneTest {

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainAnyOfTheGivenElements() {
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsNone(2, 3);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForIntArray(Target::getValue).containsNone(new int[] {1, 5}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Integer> iterable = Collections.singletonList(1);
        assertDoesNotThrow(() -> validator.ruleForIntArray(Target::getValue).containsNone(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsNone(2, 3);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsNone(0, 2, 1);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForIntArray(Target::getValue).containsNone((List<Integer>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new int[] {1, 2, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsNone(0, 1, 2, 3);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must not contain [0, 1, 2, 3] but found the following: [1, 2].", validationResult.getViolations().get(0).getErrorMessage());
    }
}
