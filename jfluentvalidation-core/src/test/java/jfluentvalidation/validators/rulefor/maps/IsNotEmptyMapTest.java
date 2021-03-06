package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotEmptyMapTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotEmpty() {
        Target t = new Target(new HashMap<String, String>() {{
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isNotEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isNotEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsEmpty() {
        Target t = new Target(new HashMap<>());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isNotEmpty();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
