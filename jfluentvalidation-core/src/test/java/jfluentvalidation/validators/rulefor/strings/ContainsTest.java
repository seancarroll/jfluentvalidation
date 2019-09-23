package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).contains("hello");

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
    void shouldThrowIllegalArgumentExceptionWhenGivenSequenceIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        NullPointerException ex = assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).contains((String) null));
        assertEquals("CharSequence elements should not be null but found one at index 0", ex.getMessage());
    }

}
