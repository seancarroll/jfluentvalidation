package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValue() {
        Target t = new Target(Collections.singletonList("hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).contains("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsValuesInDifferentOrder() {

    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenValues() {

    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesMoreThanOnce() {

    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesEventIfDuplicated() {

    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {

    }

//    @Test
//    public void should_pass_if_actual_contains_given_values() {
//        iterables.assertContains(someInfo(), actual, array("Luke"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_in_different_order() {
//        iterables.assertContains(someInfo(), actual, array("Leia", "Yoda"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_all_given_values() {
//        iterables.assertContains(someInfo(), actual, array("Luke", "Yoda"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_more_than_once() {
//        actual.addAll(newArrayList("Luke", "Luke"));
//        iterables.assertContains(someInfo(), actual, array("Luke"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_even_if_duplicated() {
//        iterables.assertContains(someInfo(), actual, array("Luke", "Luke"));
//    }
//
//    @Test
//    public void should_pass_if_actual_and_given_values_are_empty() {
//        actual.clear();
//        iterables.assertContains(someInfo(), actual, array());
//    }
//

    @Test
    void shouldReturnFailureWhenGivenIsEmptyAndActualIsNotNull() {

    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {

    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {

    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainsValues() {

    }


//    @Test
//    public void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> iterables.assertContains(someInfo(), actual, emptyArray()));
//    }
//
//    @Test
//    public void should_throw_error_if_array_of_values_to_look_for_is_null() {
//        assertThatNullPointerException().isThrownBy(() -> iterables.assertContains(someInfo(), actual, null))
//            .withMessage(valuesToLookForIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> iterables.assertContains(someInfo(), null, array("Yoda")))
//            .withMessage(actualIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_does_not_contain_values() {
//        AssertionInfo info = someInfo();
//        Object[] expected = { "Han", "Luke" };
//        try {
//            iterables.assertContains(info, actual, expected);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldContain(actual, expected, newLinkedHashSet("Han")));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
//
//    // ------------------------------------------------------------------------------------------------------------------
//    // tests using a custom comparison strategy
//    // ------------------------------------------------------------------------------------------------------------------
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_according_to_custom_comparison_strategy() {
//        iterablesWithCaseInsensitiveComparisonStrategy.assertContains(someInfo(), actual, array("LUKE"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_in_different_order_according_to_custom_comparison_strategy() {
//        iterablesWithCaseInsensitiveComparisonStrategy.assertContains(someInfo(), actual, array("LEIA", "yODa"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_all_given_values_according_to_custom_comparison_strategy() {
//        iterablesWithCaseInsensitiveComparisonStrategy.assertContains(someInfo(), actual, array("luke", "YODA"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_more_than_once_according_to_custom_comparison_strategy() {
//        actual.addAll(newArrayList("Luke", "Luke"));
//        iterablesWithCaseInsensitiveComparisonStrategy.assertContains(someInfo(), actual, array("LUke"));
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_given_values_even_if_duplicated_according_to_custom_comparison_strategy() {
//        iterablesWithCaseInsensitiveComparisonStrategy.assertContains(someInfo(), actual, array("LUke", "LuKe"));
//    }
//
//    @Test
//    public void should_fail_if_actual_does_not_contain_values_according_to_custom_comparison_strategy() {
//        AssertionInfo info = someInfo();
//        Object[] expected = { "Han", "Luke" };
//        try {
//            iterablesWithCaseInsensitiveComparisonStrategy.assertContains(info, actual, expected);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldContain(actual, expected, newLinkedHashSet("Han"), comparisonStrategy));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
//
}
