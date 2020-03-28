package jfluentvalidation.validators.rulefor.arrays.objects;

import com.google.common.collect.Sets;
import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsExactlyTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesExactly() {
        Target t = new Target(new String[] {"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly("hello", "world");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForObjectArray(Target::getValue).containsExactly(new String[] {"hello", "world"}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForObjectArray(Target::getValue).containsExactly(Sets.newHashSet("hello", "world")));
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new String[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenValuesExactlyButInDifferentOrder() {
        Target t = new Target(new String[] {"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly("world", "hello");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value have the same elements but not in the same order, at index 0 actual element was hello", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenArraysHaveDifferentSizes() {
        Target t = new Target(new String[] {"hello", "world", "foo"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly("hello", "world");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value elements were not expected [foo]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenExpectedValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new String[] {"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenExpectedValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForObjectArray(Target::getValue).containsExactly((Iterable<String>) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainGivenValuesExactly() {
        Target t = new Target(new String[] {"hello", "world", "bar"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly("hello", "world", "baz");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("value missing the following elements [baz] and the following elements were not expected [bar]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAllGivenValuesButSizeIsDifferent() {
        Target t = new Target(new String[] {"hello", "world"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).containsExactly("hello", "world", "world");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
