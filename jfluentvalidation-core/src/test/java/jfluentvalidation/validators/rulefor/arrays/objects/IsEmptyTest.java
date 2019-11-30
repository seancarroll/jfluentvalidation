package jfluentvalidation.validators.rulefor.arrays.objects;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEmptyTest {

    @Test
    void shouldNotReturnFailureWhenActualIsEmpty() {
        Target t = new Target(new String[0]);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).isEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).isEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotEmpty() {
        Target t = new Target(new String[] {"hello"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).isEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
