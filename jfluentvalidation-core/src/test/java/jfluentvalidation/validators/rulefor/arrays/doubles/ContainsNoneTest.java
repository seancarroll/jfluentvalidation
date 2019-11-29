package jfluentvalidation.validators.rulefor.arrays.doubles;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsNoneTest {

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainAnyOfTheGivenElements() {
        Target t = new Target(new double[] {1d, 5d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsNone(2d, 3d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsNone(2d, 3d);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new double[] {1d, 5d});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).containsNone(0d, 2d, 1d);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForDoubleArray(Target::getValue).containsNone((List<Double>) null));
    }
}
