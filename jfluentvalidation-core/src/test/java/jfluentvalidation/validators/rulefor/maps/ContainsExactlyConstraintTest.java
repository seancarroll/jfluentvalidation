package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.data.MapEntry.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsExactlyConstraintTest {

    private final static LinkedHashMap<String, String> actual;
    static {
        actual = new LinkedHashMap<>(2);
        actual.put("foo", "bar");
        actual.put("hello", "world");
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(entry("hello", "world"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    void shouldThrowWhenGivenEntryIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).containsExactly((Map.Entry[]) null));
    }

    @Test
    void shouldThrowWhenGivenMapIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).containsExactly((Map<String, String>) null));
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldReturnFailureWhenGivenEntriesIsEmpty() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(new MapEntry[0]);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map elements were not expected [foo=bar, hello=world]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenGivenMapIsEmpty() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Map<String, String> map = new HashMap<>(0);
        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map elements were not expected [foo=bar, hello=world]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenEntriesAreEmpty() {
        Target t = new Target(emptyMap());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        Map<String, String> map = new HashMap<>(0);
        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenMapAreEmpty() {
        Target t = new Target(emptyMap());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(emptyMap());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenEntriesAreEqualAndInSameOrder() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(entry("foo", "bar"), entry("hello", "world"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualAndGivenMapAreEqualAndInSameOrder() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> map = new LinkedHashMap<>(2);
        map.put("foo", "bar");
        map.put("hello", "world");

        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualAndGivenEntriesAreEqualAndNotInSameOrder() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(entry("hello", "world"), entry("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map have the same elements but not in the same order, at index 0 actual element was foo=bar", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualAndGivenMapAreEqualAndNotInSameOrder() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> map = new LinkedHashMap<>(2);
        map.put("hello", "world");
        map.put("foo", "bar");
        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map have the same elements but not in the same order, at index 0 actual element was foo=bar", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualAndGivenEntriesHaveDifferentSizes() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(entry("foo", "bar"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map elements were not expected [hello=world]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualAndGivenMapHaveDifferentSizes() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> map = new LinkedHashMap<>(1);
        map.put("foo", "bar");
        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map elements were not expected [hello=world]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsUnexpectedEntry() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(entry("foo", "bar"), entry("fizz", "buzz"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map missing the following elements [fizz=buzz] and the following elements were not expected [hello=world]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainUnexpectedItem() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> map = new LinkedHashMap<>(2);
        map.put("foo", "bar");
        map.put("fizz", "buzz");
        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map missing the following elements [fizz=buzz] and the following elements were not expected [hello=world]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainsEntryKeyWithDifferentValue() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).containsExactly(entry("foo", "boo"), entry("hello", "world"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map missing the following elements [foo=boo] and the following elements were not expected [foo=bar]", validationResult.getViolations().get(0).getErrorMessage());
    }

    @Test
    void shouldReturnFailureWhenActualContainMapKeyWithDifferentValue() {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        Map<String, String> map = new LinkedHashMap<>(2);
        map.put("foo", "boo");
        map.put("hello", "world");
        validator.ruleForMap(Target::getMap).containsExactly(map);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
        assertEquals("map missing the following elements [foo=boo] and the following elements were not expected [foo=bar]", validationResult.getViolations().get(0).getErrorMessage());
    }

}
