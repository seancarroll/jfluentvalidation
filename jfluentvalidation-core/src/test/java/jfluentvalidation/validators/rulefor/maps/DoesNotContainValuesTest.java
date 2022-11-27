package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static jfluentvalidation.common.MoreArrays.array;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoesNotContainValuesTest {

    // TODO: how to handle when given is empty

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainGivenValues() {
        Target t = new Target(Map.of("hello", "world", "foo", "bar"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues("baz");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues("world", "bar");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenValuesIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).doesNotContainValues((String[]) null));
    }

    @Test
    void shouldReturnFailureWhenGivenValueIsNull() {
        Target t = new Target(new HashMap<>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues((String) null);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @MethodSource("shouldReturnFailureWhenActualContainsAtLeastOneGivenValueSource")
    void shouldReturnFailureWhenActualContainsAtLeastOneGivenValue(String[] values) {
        Target t = new Target(Map.of("hello", "world", "foo", "bar"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainValues(values);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    private static Stream<Arguments> shouldReturnFailureWhenActualContainsAtLeastOneGivenValueSource() {
        return Stream.of(
            Arguments.of((Object) array("world")),
            Arguments.of((Object) array("baz", "bar"))
        );
    }
}
