package jfluentvalidation.validators.rulefor.arrays.bytes;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.FIVE;
import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.ONE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAllTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValues() {
        Target t = new Target(new byte[] {ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(ONE, FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForByteArray(Target::getValue).containsAll(new byte[]{ONE, FIVE}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Byte> iterable = Collections.singletonList(ONE);
        assertDoesNotThrow(() -> validator.ruleForByteArray(Target::getValue).containsAll(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesInDifferentOrder() {
        Target t = new Target(new byte[] {ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(FIVE, ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesMoreThanOnce() {
        Target t = new Target(new byte[] {ONE, ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllExpectedValuesEvenIfDuplicated() {
        Target t = new Target(new byte[] {ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(ONE, ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new byte[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForByteArray(Target::getValue).containsAll((Iterable<Byte>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainExpectedValues() {
        Target t = new Target(new byte[] {ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new byte[] {ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAll(ONE, FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must contain [1, 5] but could not find [5].", validationResult.getViolations().get(0).getErrorMessage());
    }
}
