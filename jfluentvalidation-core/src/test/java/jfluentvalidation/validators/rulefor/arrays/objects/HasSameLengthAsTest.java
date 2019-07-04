package jfluentvalidation.validators.rulefor.arrays.objects;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HasSameLengthAsTest {

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToGivenIterable() {
        Target t = new Target(new String[]{"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(Arrays.asList("foo", "bar"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualLengthIsEqualToGivenArray() {
        Target t = new Target(new String[]{"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(new String[]{"foo", "bar"});

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLengthIsNotEqualToGivenIterable() {
        Target t = new Target(new String[]{"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(new String[]{"foo"});

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(Arrays.asList("foo", "bar"));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForObjectArray(Target::getValue).hasSameLengthAs(null));
    }

}
