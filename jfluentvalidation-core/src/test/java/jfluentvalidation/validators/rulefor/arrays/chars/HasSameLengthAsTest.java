package jfluentvalidation.validators.rulefor.arrays.chars;

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
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(new char[] {'a'});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedByteArray() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(new Character[] {'a'});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToExpectedList() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(Collections.singletonList('a'));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(new Character[] {'a'});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedPrimitiveByteArray() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(new char[] {'a', 'b'});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedByteArray() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(new Character[] {'a', 'b'});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToExpectedList() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).hasSameLengthAs(Arrays.asList('a', 'b'));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
