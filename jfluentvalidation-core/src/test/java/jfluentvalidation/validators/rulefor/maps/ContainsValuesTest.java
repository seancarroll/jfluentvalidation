package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

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

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenValuesAreEmpty() {
        Target t = new Target(new HashMap<>());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
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

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainValue() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsValues("world", "baz");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
