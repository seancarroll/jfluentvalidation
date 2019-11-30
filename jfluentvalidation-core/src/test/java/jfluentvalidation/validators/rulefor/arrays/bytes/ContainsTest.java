package jfluentvalidation.validators.rulefor.arrays.bytes;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.ONE;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValue() {
        Target t = new Target(new byte[]{ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).contains(ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    // TODO: more tests

}
