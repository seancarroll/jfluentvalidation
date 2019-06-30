package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContainsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsSequence() {
        Target t = new Target("Hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("He");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenSequences() {
        Target t = new Target("Hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("He", "lo");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainSequence() {
        Target t = new Target("Hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainAllGivenSequences() {
        Target t = new Target("Hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("He", "lo", "world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsSequenceWithDifferentCase() {
        Target t = new Target("Hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("he");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenGivenSequenceIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        NullPointerException ex = assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).contains((String) null));
        assertEquals("CharSequence elements should not be null but found one at index 0", ex.getMessage());
    }

//    @Test
//    public void should_fail_if_actual_does_not_contain_sequence() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContains(someInfo(), "Yoda", "Luke"))
//            .withMessage(shouldContain("Yoda", "Luke").create());
//    }
//
//    @Test
//    public void should_fail_if_actual_contains_sequence_but_in_different_case() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContains(someInfo(), "Yoda", "yo"))
//            .withMessage(shouldContain("Yoda", "yo").create());
//    }
//
//    @Test
//    public void should_throw_error_if_sequence_is_null() {
//        assertThatNullPointerException().isThrownBy(() -> strings.assertContains(someInfo(), "Yoda", (String) null))
//            .withMessage(charSequenceToLookForIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContains(someInfo(), null, "Yoda"))
//            .withMessage(actualIsNull());
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_sequence() {
//        strings.assertContains(someInfo(), "Yoda", "Yo");
//    }
//
//    @Test
//    public void should_fail_if_actual_does_not_contain_all_given_strings() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertContains(someInfo(), "Yoda", "Yo", "da", "Han"))
//            .withMessage(shouldContain("Yoda", array("Yo", "da", "Han"), newLinkedHashSet("Han")).create());
//    }
//
//    @Test
//    public void should_pass_if_actual_contains_all_given_strings() {
//        strings.assertContains(someInfo(), "Yoda", "Yo", "da");
//    }

}
