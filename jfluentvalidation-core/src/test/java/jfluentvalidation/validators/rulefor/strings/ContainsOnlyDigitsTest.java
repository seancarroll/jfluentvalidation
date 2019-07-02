package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsOnlyDigitsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsOnlyDigits() {
        Target t = new Target("109");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsOnlyDigits();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "&", " ", "$10"})
    void shouldReturnFailureWhenActualContainsAnyNonDigitCharacter(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsOnlyDigits();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsOnlyDigits();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEmpty() {
        Target t = new Target("");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsOnlyDigits();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

//    @Test
//    public void should_pass_if_actual_contains_only_digits() {
//        strings.assertContainsOnlyDigits(someInfo(), "10");
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContainsOnlyDigits(someInfo(), null))
//            .withMessage(actualIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_contains_any_non_digit_character() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContainsOnlyDigits(someInfo(), "10$"))
//            .withMessage(shouldContainOnlyDigits("10$", '$', 2).create());
//    }
//
//    /**
//     * See <a href="https://github.com/joel-costigliola/assertj-core/pull/342">discussion on failing the assertion for empty CharSequence</a>
//     */
//    @Test
//    public void should_fail_if_actual_is_empty() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContainsOnlyDigits(someInfo(), ""))
//            .withMessage(shouldContainOnlyDigits("").create());
//    }
}
