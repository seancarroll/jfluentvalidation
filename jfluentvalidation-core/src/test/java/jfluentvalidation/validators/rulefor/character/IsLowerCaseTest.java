package jfluentvalidation.validators.rulefor.character;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsLowerCaseTest {

    @Test
    void shouldNotReturnFailureWhenActualIsLowerCase() {
        Target t = new Target('i');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isLowerCase();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsEmpty() {
        Target t = new Target(' ');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isLowerCase();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target('\u0000');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isLowerCase();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }


    @Test
    void shouldReturnFailureWhenActualIsUpperCase() {
        Target t = new Target('I');

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForCharacter(Target::getValue).isLowerCase();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
