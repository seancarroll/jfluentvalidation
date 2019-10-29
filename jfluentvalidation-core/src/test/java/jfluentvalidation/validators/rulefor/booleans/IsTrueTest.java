package jfluentvalidation.validators.rulefor.booleans;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsTrueTest {

    // TODO: test for primitive and non-primitive

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isTrue();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsTrue() {
        Target t = new Target(true);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isTrue();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsFalse() {
        Target t = new Target(false);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isTrue();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
