package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsSubStringOfTest {

    @Test
    void shouldNotReturnFailureWhenActualIsASubStringOfGiven() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isSubstringOf("hello world");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsEqualToGiven() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isSubstringOf("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello", ""})
    void shouldNotReturnFailureWhenActualIsEmpty(String given) {
        Target t = new Target("");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isSubstringOf(given);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGiven() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isSubstringOf("el");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsDifferentFromGiven() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isSubstringOf("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isSubstringOf("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).isSubstringOf(null));
    }


//    @Test
//    public void should_pass_if_actual_is_a_substring_of_given_string() {
//        strings.assertIsSubstringOf(someInfo(), "Yo", "Yoda");
//    }
//
//    @Test
//    public void should_pass_if_actual_is_equal_to_given_string() {
//        strings.assertIsSubstringOf(someInfo(), "Yoda", "Yoda");
//    }
//
//    @Test
//    public void should_pass_if_actual_is_empty() {
//        strings.assertIsSubstringOf(someInfo(), "", "Yoda");
//        strings.assertIsSubstringOf(someInfo(), "", "");
//    }
//
//    @Test
//    public void should_fail_if_actual_contains_given_string() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertIsSubstringOf(someInfo(), "Yoda", "oda"))
//            .withMessage(shouldBeSubstring("Yoda", "oda", StandardComparisonStrategy.instance()).create());
//    }
//
//    @Test
//    public void should_fail_if_actual_completely_different_from_given_string() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertIsSubstringOf(someInfo(), "Yoda", "Luke"))
//            .withMessage(shouldBeSubstring("Yoda", "Luke", StandardComparisonStrategy.instance()).create());
//    }
//
//    @Test
//    public void should_throw_error_if_sequence_is_null() {
//        assertThatNullPointerException().isThrownBy(() -> strings.assertIsSubstringOf(someInfo(), "Yoda", null))
//            .withMessage("Expecting CharSequence not to be null");
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertIsSubstringOf(someInfo(), null, "Yoda"))
//            .withMessage(actualIsNull());
//    }
}
