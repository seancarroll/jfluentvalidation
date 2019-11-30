package jfluentvalidation.validators.rulefor.arrays.longs;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasSameLengthAsTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedPrimitiveByteArray() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(new long[] {1});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedByteArray() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(new Long[] {1L});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedList() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(Collections.singletonList(1L));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(new Long[] {1L});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedPrimitiveByteArray() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(new long[] {1, 2});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedByteArray() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(new Long[] {1L, 2L});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedList() {
        Target t = new Target(new long[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLongArray(Target::getValue).hasSameLengthAs(Arrays.asList(1L, 2L));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
