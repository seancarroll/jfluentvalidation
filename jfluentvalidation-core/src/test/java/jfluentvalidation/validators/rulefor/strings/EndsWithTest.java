package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EndsWithTest {

    @Test
    void shouldNotReturnFailureWhenActualEndsWithPrefix() {
        Target t = new Target("hello world");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).endsWith("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotEndWithPrefix() {
        Target t = new Target("hello world");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).endsWith("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).endsWith("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenPrefixIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).endsWith(null));
    }



//    @Test
//    public void should_fail_if_actual_does_not_end_with_suffix() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertEndsWith(someInfo(), "Yoda", "Luke"))
//            .withMessage(shouldEndWith("Yoda", "Luke").create());
//    }
//
//    @Test
//    public void should_throw_error_if_suffix_is_null() {
//        assertThatNullPointerException().isThrownBy(() -> strings.assertEndsWith(someInfo(), "Yoda", null))
//            .withMessage("The given suffix should not be null");
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> strings.assertEndsWith(someInfo(), null, "Yoda"))
//            .withMessage(actualIsNull());
//    }

}
