package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.common.Maps;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.validators.DefaultValidator;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.util.Arrays.array;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoesNotContainTest {

    // TODO: how to handle when given is empty

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainGivenEntries() {
        Target t = new Target(Map.of("hello", "world", "foo", "bar"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain(array(entry("name", "sean")));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    // TODO: not sure this is appropriate
    @SuppressWarnings("unchecked")
    @Test
    void shouldThrowExceptionWhenGivenEntriesIsEmpty() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(IllegalArgumentException.class, () -> validator.ruleForMap(Target::getMap).doesNotContain(new MapEntry[0]));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain(array(entry("hello", "world")));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenEntriesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).doesNotContain((Map.Entry<String, String>[]) null));
    }

    @Test
    void shouldReturnFailureWhenGivenEntryIsNull() {
        Target t = new Target(Map.of("hello", "world", "foo", "bar"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain((Map.Entry<? extends String, ? extends String>) null);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenEntry() {
        Target t = new Target(Map.of("hello", "world", "foo", "bar"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain(array(entry("hello", "world"), entry("name", "sean")));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    // TODO: use this or remove
    private static Stream<Arguments> shouldReturnFailureWhenActualContainsAtLeastOneGivenValueSource() {
        return Stream.of(
            Arguments.of((Object) MoreArrays.array(Maps.entry("hello", "world"))),
            Arguments.of((Object) MoreArrays.array(Maps.entry("name", "sean"), Maps.entry("foo", "bar")))
        );
    }
}
