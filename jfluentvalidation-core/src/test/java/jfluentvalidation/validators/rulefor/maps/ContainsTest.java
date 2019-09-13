package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.util.Arrays.array;
import static org.junit.jupiter.api.Assertions.*;

class ContainsTest {

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenEntry() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(array(entry("hello", "world")));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsGivenEntriesInDifferentOrder() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
            put("name", "sean");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(array(entry("name", "sean"), entry("hello", "world")));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsAllGivenEntries() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(array(entry("hello", "world"), entry("foo", "bar")));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldNotReturnFailureWhenActualAndGivenEntriesAreEmpty() {
        Target t = new Target(new HashMap<>());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(new MapEntry[0]);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(array(entry("hello", "world")));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowWhenGivenEntriesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).contains((Map.Entry<String, String>[]) null));
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnFailureWhenGivenEntriesIsEmpty() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(new MapEntry[0]);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnFailureWhenGivenEntryIsNull() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(new MapEntry[]{null});

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainEntries() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).contains(array(entry("hello", "world"), entry("name", "sean")));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
