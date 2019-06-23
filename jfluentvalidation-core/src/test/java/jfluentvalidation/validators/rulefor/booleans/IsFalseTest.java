package jfluentvalidation.validators.rulefor.booleans;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsFalseTest {

    // TODO: best for primitive and non-primitive

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isFalse();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsFalse() {
        Target t = new Target(false);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isFalse();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsTrue() {
        Target t = new Target(true);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBoolean(Target::isPresent).isFalse();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
