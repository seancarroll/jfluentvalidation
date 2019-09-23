package jfluentvalidation.validators.rulefor.arrays.floats;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasLengthTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpected() {
        Target t = new Target(new float[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).hasLength(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).hasLength(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpected() {
        Target t = new Target(new float[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).hasLength(2);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenExpectedLengthIsNegative() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(IllegalArgumentException.class, () -> validator.ruleForFloatArray(Target::getValue).hasLength(-1));
    }
}
