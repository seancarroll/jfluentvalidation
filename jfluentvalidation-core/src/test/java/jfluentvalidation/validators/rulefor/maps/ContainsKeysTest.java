package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsKeyTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenKey() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsKeys("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenKeyIsNullAndGivenIsNull() {
        Target t = new Target(new HashMap<String, String>() {{
            put(null, "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsKeys(null);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainKey() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsKeys("foo");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
