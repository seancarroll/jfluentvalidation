package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoesNotContainKeysTest {

    // TODO: how to handle when given is empty

    @Test
    void shouldNotReturnFailureWhenActualDoesNotContainGivenKeys() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainKeys("baz");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainKeys("hello", "foo");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenKeysIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForMap(Target::getMap).doesNotContainKeys((String[]) null));
    }

    @Test
    void shouldReturnFailureWhenGivenKeyIsNull() {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainKeys((String) null);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @ParameterizedTest
    @MethodSource("shouldReturnFailureWhenActualContainsAtLeastOneGivenKeySource")
    void shouldReturnFailureWhenActualContainsAtLeastOneGivenKey(String[] keys) {
        Target t = new Target(new HashMap<String, String>() {{
            put("hello", "world");
            put("foo", "bar");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).doesNotContainKeys(keys);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    private static Stream<Arguments> shouldReturnFailureWhenActualContainsAtLeastOneGivenKeySource() {
        return Stream.of(
            Arguments.of((Object) new String[]{"hello"}),
            Arguments.of((Object) new String[] {"baz", "foo"})
        );
    }

}
