package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoesNotContainAnyWhitespacesTest {

    @ParameterizedTest
    @MethodSource("doesNotContainAnyWhitespaces")
    void shouldNotReturnFailureWhenActualDoesNotContainWhitespaces(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).doesNotContainAnyWhitespaces();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    static Stream<String> doesNotContainAnyWhitespaces() {
        return Stream.of(null,
            "",
            "a",
            "bc");
    }

    @ParameterizedTest
    @MethodSource("containsWhitespaces")
    void shouldReturnFailureWhenActualContainsWhitespaces(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).doesNotContainAnyWhitespaces();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    static Stream<String> containsWhitespaces() {
        return Stream.of("a ",
            "a b",
            "a  b",
            "a\u005Ctb", // tab
            "a\u005Cnb", // line feed
            "a\u005Crb", // carriage return
            "a \u005Cn\u005Cr  b");
    }
}
