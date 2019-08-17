package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualToIgnoringCaseTest {

    @Test
    void shouldReturnFailureWhenActualIsNullAndExpectedIsNot() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWHenActualIsNotNullAndExpectedIs() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase(null);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotEqualExpected() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndExpectedAreNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase(null);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndExpectedAreTheSame() {
        String s = "hello";
        Target t = new Target(s);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase(s);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndExpectedEqualButNotTheSame() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndExpectedEqualIgnoringCase() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringCase("HeLLo");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

//    @Test
//    public void should_fail_if_actual_is_null_and_expected_is_not() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertEqualsIgnoringCase(someInfo(), null, "Luke"))
//            .withMessage(shouldBeEqual(null, "Luke").create());
//    }
//
//    @Test
//    public void should_fail_if_actual_is_not_null_and_expected_is() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertEqualsIgnoringCase(someInfo(), "Luke", null))
//            .withMessage(shouldBeEqual("Luke", null).create());
//    }
//
//    @Test
//    public void should_fail_if_both_Strings_are_not_equal_regardless_of_case() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertEqualsIgnoringCase(someInfo(), "Yoda", "Luke"))
//            .withMessage(shouldBeEqual("Yoda", "Luke").create());
//    }
//
//    @Test
//    public void should_pass_if_both_Strings_are_null() {
//        strings.assertEqualsIgnoringCase(someInfo(), null, null);
//    }
//
//    @Test
//    public void should_pass_if_both_Strings_are_the_same() {
//        String s = "Yoda";
//        strings.assertEqualsIgnoringCase(someInfo(), s, s);
//    }
//
//    @Test
//    public void should_pass_if_both_Strings_are_equal_but_not_same() {
//        strings.assertEqualsIgnoringCase(someInfo(), "Yoda", new String(arrayOf('Y', 'o', 'd', 'a')));
//    }
//
//    @Test
//    public void should_pass_if_both_Strings_are_equal_ignoring_case() {
//        strings.assertEqualsIgnoringCase(someInfo(), "Yoda", "YODA");
//    }
}
