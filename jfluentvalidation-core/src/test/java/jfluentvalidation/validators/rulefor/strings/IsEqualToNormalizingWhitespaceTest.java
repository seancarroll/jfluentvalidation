package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualToNormalizingWhitespaceTest {

    @Test
    void shouldReturnFailureWhenActualIsNullAndExpectedIsNot() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToNormalizingWhitespace("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotNullAndExpectedIs() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToNormalizingWhitespace(null);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualAndExpectedAreNotEqualAfterNormalizingWhitespace() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToNormalizingWhitespace("world");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("equalNormalizingWhitespaceGenerator")
    void shouldNotReturnFailureWhenActualAndExpectedAreEqualAfterNormalizingWhitespace(String actual, String expected) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToNormalizingWhitespace(expected);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    private static Stream<Arguments> equalNormalizingWhitespaceGenerator() {
        return Stream.of(
            Arguments.of("hello   world", "hello world"),
            Arguments.of("  hello world  ", "hello world"),
            Arguments.of(" hello\tworld", " hello world"),
            Arguments.of("       ", " "),
            Arguments.of(" hello\tworld ", " hello\tworld "),
            Arguments.of(null, null),
            Arguments.of(" \t \t", " "),
            Arguments.of(" hello", "hello "));
    }

}
