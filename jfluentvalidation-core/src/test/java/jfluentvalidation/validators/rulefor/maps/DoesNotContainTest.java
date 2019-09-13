package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.common.Maps;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.validators.DefaultValidator;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.util.Arrays.array;
import static org.junit.jupiter.api.Assertions.*;

class DoesNotContainTest {

    // TODO: how to handle when given is empty

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainGivenEntries() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain(array(entry("name", "sean")));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
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

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenEntriesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).doesNotContain((Map.Entry<String, String>[]) null));
    }

    @Test
    void shouldReturnFailureWhenGivenEntryIsNull() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain((Map.Entry<? extends String, ? extends String>) null);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualContainsGivenEntry() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContain(array(entry("hello", "world"), entry("name", "sean")));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    private static Stream<Arguments> shouldReturnFailureWhenActualContainsAtLeastOneGivenValueSource() {
        return Stream.of(
            Arguments.of((Object) MoreArrays.array(Maps.entry("hello", "world"))),
            Arguments.of((Object) MoreArrays.array(Maps.entry("name", "sean"), Maps.entry("foo", "bar")))
        );
    }
}
