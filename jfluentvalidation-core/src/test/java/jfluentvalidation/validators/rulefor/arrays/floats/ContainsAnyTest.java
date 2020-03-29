package jfluentvalidation.validators.rulefor.arrays.floats;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsAnyTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValues() {
        Target t = new Target(new float[] {1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(1f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForFloatArray(Target::getValue).containsAny(new float[]{1f, 5f}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Float> iterable = Collections.singletonList(1f);
        assertDoesNotThrow(() -> validator.ruleForFloatArray(Target::getValue).containsAny(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenValues() {
        Target t = new Target(new float[] {1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(1f, 5f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesInDifferentOrder() {
        Target t = new Target(new float[] {1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(5f, 1f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesMoreThanOnce() {
        Target t = new Target(new float[] {1f, 1f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(1f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsJustOneOfTheGivenValues() {
        Target t = new Target(new float[] {1f, 1f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(1f, 5f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesEvenIfDuplicated() {
        Target t = new Target(new float[] {1f, 1f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(1f, 1f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new float[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(1f);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenGivenValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new float[] {1f, 5f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainAnyOfTheGivenValues() {
        Target t = new Target(new float[] {1f});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(5f);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFloatArray(Target::getValue).containsAny((Iterable<Float>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new float[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFloatArray(Target::getValue).containsAny(2f, 3f);

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must contain at least one of the following: [2.0, 3.0] but none were found.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
