package jfluentvalidation.validators.rulefor.arrays.doubles;

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
        Target t = new Target(new double[] {1});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(new double[] {1});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedByteArray() {
        Target t = new Target(new double[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(new Double[] {1d});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedList() {
        Target t = new Target(new double[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(Collections.singletonList(1d));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(new Double[] {1d});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedPrimitiveByteArray() {
        Target t = new Target(new double[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(new double[] {1d, 2d});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedByteArray() {
        Target t = new Target(new double[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(new Double[] {1d, 2d});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedList() {
        Target t = new Target(new double[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDoubleArray(Target::getValue).hasSameLengthAs(Arrays.asList(1d, 2d));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
