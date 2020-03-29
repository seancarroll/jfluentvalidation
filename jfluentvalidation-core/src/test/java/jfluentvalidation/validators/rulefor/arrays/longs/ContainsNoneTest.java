package jfluentvalidation.validators.rulefor.arrays.longs;

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
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(2L, 3L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForLongArray(Target::getValue).containsNone(new long[]{1L, 5L}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Long> iterable = Collections.singletonList(1L);
        assertDoesNotThrow(() -> validator.ruleForLongArray(Target::getValue).containsNone(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(2L, 3L);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(0L, 2L, 1L);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLongArray(Target::getValue).containsNone((List<Long>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new long[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(1L, 5L, 10L);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must not contain [1, 5, 10] but found the following: [1, 5].", validationResult.getViolations().get(0).getErrorMessage());
    }
}
