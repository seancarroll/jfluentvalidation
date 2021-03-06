package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualsMapTest {

    @Test
    void shouldNotReturnFailureWhenActualReferenceEqualsGiven() {
        Map<String, String> reference = new HashMap<>();
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isEqualTo(reference);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualRepresentsTheSameMappingAsGiven() {
        Map<String, String> actual = new HashMap<String, String>() {{
            put("key1", "value1");
        }};
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> other = new HashMap<String, String>() {{
            put("key1", "value1");
        }};
        validator.ruleForMap(Target::getMap).isEqualTo(other);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isEqualTo(new HashMap<>());

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotRepresentTheSameMappingAsGiven() {
        Map<String, String> actual = new HashMap<String, String>() {{
            put("key1", "value1");
        }};
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> other = new HashMap<String, String>() {{
            put("Key1", "Value1");
        }};
        validator.ruleForMap(Target::getMap).isEqualTo(other);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
