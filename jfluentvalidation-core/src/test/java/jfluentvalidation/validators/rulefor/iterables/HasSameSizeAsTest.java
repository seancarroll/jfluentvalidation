package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasSameSizeAsTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToGivenIterable() {
        Target t = new Target(Arrays.asList("hello", "world"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSameSizeAs(Arrays.asList("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToGivenArray() {
        Target t = new Target(Arrays.asList("hello", "world"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSameSizeAs(Arrays.asList("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSameSizeAs(Arrays.asList("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToGivenIterable() {
        Target t = new Target(Arrays.asList("hello", "world"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).hasSameSizeAs(Collections.singletonList("foo"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        assertThrows(NullPointerException.class, () -> validator.ruleForIterable(Target::getValue).hasSameSizeAs(null));
    }

}
