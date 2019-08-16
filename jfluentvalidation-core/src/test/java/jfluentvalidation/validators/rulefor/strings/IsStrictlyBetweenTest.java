package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class IsStrictlyBetweenTest {


    // TODO: add tests for between override inclusive false
    // TODO: test for different cases

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).isStrictlyBetween(null, "z"));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).isStrictlyBetween("a", null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsInRange() {
        Target t = new Target("banana");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isStrictlyBetween("apple", "carrot");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target("apple");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isStrictlyBetween("apple", "carrot");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target("carrot");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isStrictlyBetween("apple", "carrot");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isStrictlyBetween("apple", "carrot");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target("apple");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isStrictlyBetween("banana", "carrot");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target("mango");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isStrictlyBetween("apple", "carrot");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


}
