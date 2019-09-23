package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).endsWith("hello");

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
    void shouldThrowExceptionWhenPrefixIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).endsWith(null));
    }

}
