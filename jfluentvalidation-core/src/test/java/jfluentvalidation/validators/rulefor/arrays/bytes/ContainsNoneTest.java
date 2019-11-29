package jfluentvalidation.validators.rulefor.arrays.bytes;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.FIVE;
import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.ONE;
import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.TEN;
import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.ZERO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsNoneTest {

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainAnyOfTheGivenElements() {
        Target t = new Target(new byte[] {ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsNone(ZERO, TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsNone(ZERO, TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new byte[] {ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsNone(ZERO, TEN, ONE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForByteArray(Target::getValue).containsNone((List<Byte>) null));
    }
}
