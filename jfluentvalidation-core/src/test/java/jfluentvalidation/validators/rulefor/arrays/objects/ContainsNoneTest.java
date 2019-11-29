package jfluentvalidation.validators.rulefor.arrays.objects;

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
        Target t = new Target(new String[] {"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsNone("foo", "baz");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsNone("foo", "baz");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new String[] {"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsNone("foo", "baz", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForObjectArray(Target::getValue).containsNone((List<String>) null));
    }
}
