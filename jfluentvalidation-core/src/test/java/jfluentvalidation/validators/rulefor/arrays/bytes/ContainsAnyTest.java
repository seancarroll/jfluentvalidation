package jfluentvalidation.validators.rulefor.arrays.bytes;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.FIVE;
import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.ONE;
import static jfluentvalidation.validators.rulefor.arrays.bytes.Bytes.TEN;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAnyTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValues() {
        Target t = new Target(new byte[]{ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForByteArray(Target::getValue).containsAny(new byte[]{ONE, FIVE}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Byte> iterable = Collections.singletonList(ONE);
        assertDoesNotThrow(() -> validator.ruleForByteArray(Target::getValue).containsAny(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenValues() {
        Target t = new Target(new byte[]{ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(ONE, FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesInDifferentOrder() {
        Target t = new Target(new byte[]{ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(FIVE, ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesMoreThanOnce() {
        Target t = new Target(new byte[]{ONE, ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsJustOneOfTheGivenValues() {
        Target t = new Target(new byte[]{ONE, ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(ONE, FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesEvenIfDuplicated() {
        Target t = new Target(new byte[]{ONE, ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(ONE, ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new byte[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(ONE);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenGivenValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new byte[]{ONE, FIVE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainAnyOfTheGivenValues() {
        Target t = new Target(new byte[]{ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(FIVE);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForByteArray(Target::getValue).containsAny((Iterable<Byte>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new byte[] {ONE});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForByteArray(Target::getValue).containsAny(FIVE, TEN);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must contain at least one of the following: [5, 10] but none were found.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
