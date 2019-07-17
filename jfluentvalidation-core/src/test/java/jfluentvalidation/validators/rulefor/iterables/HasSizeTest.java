package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasSizeTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpected() {
        Target t = new Target(Arrays.asList("hello", "world"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSize(2);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSize(2);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpected() {
        Target t = new Target(Arrays.asList("hello", "world"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSize(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
