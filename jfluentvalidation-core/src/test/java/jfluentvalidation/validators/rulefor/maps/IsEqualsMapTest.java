package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualsMapTest {

    @Test
    void shouldNotReturnFailureWhenActualReferenceEqualsGiven() {
        Map<String, String> reference = new HashMap<>();
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isEquals(reference);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
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
        validator.ruleForMap(Target::getMap).isEquals(other);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).isEquals(new HashMap<>());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
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
        validator.ruleForMap(Target::getMap).isEquals(other);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
