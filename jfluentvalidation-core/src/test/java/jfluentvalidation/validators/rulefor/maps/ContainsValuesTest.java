package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsValuesTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenValues() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("world", "bar");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new HashMap<>());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("world");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    // TODO: is this how we want to handle? make sure we are consistent between containsEntries/keys/values and doesNotContainEntires/keys/values
    @Test
    void shouldThrowExceptionWhenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).containsValues((String[]) null));
    }

    @Test
    void shouldNotReturnFailureWhenActualAndValuesContainsNull() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put(null, null);
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("world", null);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainValue() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("world", "baz");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
