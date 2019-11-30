package jfluentvalidation.validators.rulefor.arrays.objects;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasSameLengthAsTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToGivenIterable() {
        Target t = new Target(new String[]{"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(Arrays.asList("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToGivenArray() {
        Target t = new Target(new String[]{"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(new String[]{"foo", "bar"});

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(Arrays.asList("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToGivenIterable() {
        Target t = new Target(new String[]{"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(new String[]{"foo"});

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        assertThrows(NullPointerException.class, () -> validator.ruleForObjectArray(Target::getValue).hasSameLengthAs((Iterable<String>) null));
    }

}
