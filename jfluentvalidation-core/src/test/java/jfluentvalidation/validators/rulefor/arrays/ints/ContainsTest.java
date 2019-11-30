package jfluentvalidation.validators.rulefor.arrays.ints;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValue() {
        Target t = new Target(new int[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).contains(1);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    // TODO: more tests
}
