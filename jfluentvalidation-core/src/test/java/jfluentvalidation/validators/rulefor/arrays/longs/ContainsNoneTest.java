package jfluentvalidation.validators.rulefor.arrays.longs;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsNoneTest {

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainAnyOfTheGivenElements() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(2L, 3L);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(2L, 3L);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new long[] {1L, 5L});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).containsNone(0L, 2L, 1L);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLongArray(Target::getValue).containsNone((List<Long>) null));
    }

}
