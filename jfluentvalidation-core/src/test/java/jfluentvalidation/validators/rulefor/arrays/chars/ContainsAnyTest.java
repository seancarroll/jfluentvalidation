package jfluentvalidation.validators.rulefor.arrays.chars;

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
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('a');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldSupportGivenAsArray() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertDoesNotThrow(() -> validator.ruleForCharArray(Target::getValue).containsAny(new char[] {'a', 'b'}));
    }

    @Test
    void shouldSupportGivenAsIterable() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Iterable<Character> iterable = Collections.singletonList('a');
        assertDoesNotThrow(() -> validator.ruleForCharArray(Target::getValue).containsAny(iterable));
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenValues() {
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('a', 'b');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesInDifferentOrder() {
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('b', 'a');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesMoreThanOnce() {
        Target t = new Target(new char[] {'a', 'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('a');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsJustOneOfTheGivenValues() {
        Target t = new Target(new char[] {'a', 'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('a', 'b');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValuesEvenIfDuplicated() {
        Target t = new Target(new char[] {'a', 'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('a', 'a');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenAreEmpty() {
        Target t = new Target(new char[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('a');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenGivenValuesIsEmptyAndActualIsNot() {
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny(Collections.emptyList());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainAnyOfTheGivenValues() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('b');

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForCharArray(Target::getValue).containsAny((Iterable<Character>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsAny('b', 'c');

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must contain at least one of the following: [b, c] but none were found.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
