package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEqualToIgnoringWhitespaceTest {

    @Test
    void shouldReturnFailureWhenActualIsNullAndExpectedIsNot() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringWhitespace("world");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotNullAndExpectedIs() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringWhitespace(null);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualAndExpectedAreNotEqualIgnoringWhitespace() {
        Target t = new Target("hello");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringWhitespace("world");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @ParameterizedTest
    @MethodSource("equalIgnoringWhitespaceGenerator")
    void shouldNotReturnFailureWhenActualAndExpectedAreEqualIgnoringWhitespace(String actual, String expected) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isEqualToIgnoringWhitespace(expected);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    private static Stream<Arguments> equalIgnoringWhitespaceGenerator() {
        return Stream.of(
            Arguments.of("helloworld", "hello world"),
            Arguments.of("hello world", "helloworld"),
            Arguments.of("hello   world", "hello world"),
            Arguments.of("  hello world  ", "hello world"),
            Arguments.of(" hello\tworld ", " hello world"),
            Arguments.of(" hello    world ", "hello world"),
            Arguments.of(" hello    world ", "  hello world   "),
            Arguments.of("       ", " "),
            Arguments.of(" hello\tworld ", "h e l l o w o r l d"),
            Arguments.of(" hello\tworld ", " hello\tworld "),
            Arguments.of(null, null),
            Arguments.of(" \t \t", " "),
            Arguments.of(" hello", "hello "));
    }

}
