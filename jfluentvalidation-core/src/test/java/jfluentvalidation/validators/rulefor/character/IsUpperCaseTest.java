package jfluentvalidation.validators.rulefor.character;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsUpperCaseTest {

    @Test
    void shouldNotReturnFailureWhenActualIsUpperCase() {
        Target t = new Target('I');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isUpperCase();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsEmpty() {
        Target t = new Target(' ');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isUpperCase();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target('\u0000');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isUpperCase();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotUpperCase() {
        Target t = new Target('i');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isUpperCase();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
