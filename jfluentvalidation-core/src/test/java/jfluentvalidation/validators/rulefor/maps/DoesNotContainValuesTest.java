package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static jfluentvalidation.common.MoreArrays.array;
import static org.junit.jupiter.api.Assertions.*;

class DoesNotContainValuesTest {

    // TODO: how to handle when given is empty

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainGivenValues() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues("baz");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues("world", "bar");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).doesNotContainValues((String[])null));
    }

    @Test
    void shouldReturnFailureWhenGivenValueIsNull() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues((String) null);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("shouldReturnFailureWhenActualContainsAtLeastOneGivenValueSource")
    void shouldReturnFailureWhenActualContainsAtLeastOneGivenValue(String[] values) {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues(values);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    private static Stream<Arguments> shouldReturnFailureWhenActualContainsAtLeastOneGivenValueSource() {
        return Stream.of(
            Arguments.of((Object) array("world")),
            Arguments.of((Object) array("baz", "bar"))
        );
    }
}
