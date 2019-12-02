package jfluentvalidation.validators.rulefor.arrays.chars;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsNoneTest {

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainAnyOfTheGivenElements() {
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsNone('c', 'd');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsNone('c', 'd');

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsAtLeastOneOfTheGivenElements() {
        Target t = new Target(new char[] {'a', 'b'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsNone('c', 'd', 'a');

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForCharArray(Target::getValue).containsNone((List<Character>) null));
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new char[] {'a'});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharArray(Target::getValue).containsNone('a', 'b');

        ValidationResult validationResult = validator.validate(t);

        assertEquals("value must not contain [a, b] but found the following: [a].", validationResult.getViolations().get(0).getErrorMessage());
    }
}
