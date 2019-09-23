package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAllTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValues() {
        Target t = new Target(Arrays.asList("hello", "world", "foo"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesInDifferentOrder() {
        Target t = new Target(Arrays.asList("hello", "world", "foo"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("foo", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesMoreThanOnce() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesEvenIfDuplicated() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("hello", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(Collections.singletonList(""));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForIterable(Target::getValue).containsAllOf((Iterable<String>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("hello", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainExpectedValues() {
        Target t = new Target(Arrays.asList("hello", "world", "foo"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAllOf("hello", "bar");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
