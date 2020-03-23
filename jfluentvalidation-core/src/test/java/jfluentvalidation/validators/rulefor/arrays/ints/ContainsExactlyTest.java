package jfluentvalidation.validators.rulefor.arrays.ints;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsExactlyTest {

    @Test
    void t() {
        Target t = new Target(new int[] {1, 5});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIntArray(Target::getValue).containsExactly(1, 5);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

}
