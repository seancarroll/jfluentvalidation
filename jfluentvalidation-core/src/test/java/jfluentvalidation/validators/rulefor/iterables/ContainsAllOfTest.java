package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainsAllOfTest {

    // TODO: add actual and expected empty test

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

//    @Test
//    public void should_pass_if_actual_contains_all_iterable_values() {
//        iterables.assertContainsAll(someInfo(), actual, newArrayList("Luke"));
//        // order does not matter
//        iterables.assertContainsAll(someInfo(), actual, newArrayList("Leia", "Yoda"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_all_iterable_values_more_than_once() {
//        actual.addAll(newArrayList("Luke", "Luke"));
//        iterables.assertContainsAll(someInfo(), actual, newArrayList("Luke"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_all_iterable_values_even_if_duplicated() {
//        iterables.assertContainsAll(someInfo(), actual, newArrayList("Luke", "Luke"));
//    }
//
//    @Test
//    public void should_throw_error_if_array_of_values_to_look_for_is_null() {
//        assertThatNullPointerException().isThrownBy(() -> iterables.assertContainsAll(someInfo(), actual, null))
//            .withMessage(iterableToLookForIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> iterables.assertContainsAll(someInfo(), null, newArrayList("Yoda")))
//            .withMessage(actualIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_does_not_contain_values() {
//        AssertionInfo info = someInfo();
//        List<String> expected = newArrayList("Han", "Luke");
//        try {
//            iterables.assertContainsAll(info, actual, expected);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldContain(actual, expected.toArray(), newLinkedHashSet("Han")));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
}
