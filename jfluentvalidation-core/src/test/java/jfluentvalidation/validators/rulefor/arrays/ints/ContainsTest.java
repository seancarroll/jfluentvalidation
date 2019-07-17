package jfluentvalidation.validators.rulefor.arrays.ints;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValue() {
        Target t = new Target(new int[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).contains(1);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

}
