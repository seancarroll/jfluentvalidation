package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.TestValueObject;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAnyTest {

    // TODO: make sure to support varargs, arrays, list, iterables

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValues() {
        Target t = new Target(Arrays.asList("hello", "world", "foo"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenNonComparableValue() {
        TargetWithNonComparable t = new TargetWithNonComparable(Arrays.asList(new TestValueObject("hello"), new TestValueObject("hello"), new TestValueObject("hello")));

        DefaultValidator<TargetWithNonComparable> validator = new DefaultValidator<>(TargetWithNonComparable.class);
        validator.ruleForIterable(TargetWithNonComparable::getValue).containsAnyOf(new TestValueObject("hello"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenValues() {
        Target t = new Target(Arrays.asList("hello", "world", "foo"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("hello", "world", "foo");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesInDifferentOrder() {
        Target t = new Target(Arrays.asList("hello", "world", "foo"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("foo", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesMoreThanOnce() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsJustOneOfTheGivenValues() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("hello", "foo", "bar");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesEvenIfDuplicated() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("hello", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(Collections.emptyList());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf(Collections.emptyList());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf("hello", "hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenGivenValuesIsEmptyAndActualIsNot() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf(Collections.emptyList());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainAnyOfTheGivenValues() {
        Target t = new Target(Arrays.asList("hello", "hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).containsAnyOf(Arrays.asList("foo", "bar"));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForIterable(Target::getValue).containsAnyOf((Iterable<String>) null));
    }

    private class TargetWithNonComparable {
        private final List<TestValueObject> value;

        TargetWithNonComparable(List<TestValueObject> value) {
            this.value = value;
        }

        public List<TestValueObject> getValue() {
            return value;
        }
    }

}
