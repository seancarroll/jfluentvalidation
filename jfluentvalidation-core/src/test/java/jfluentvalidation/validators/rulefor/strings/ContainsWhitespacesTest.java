package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsWhitespacesTest {

    @ParameterizedTest
    @MethodSource("containOnlyWhitespace")
    void shouldNotReturnFailureWhenActualContainsOnlyWhitespace(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsWhitespaces();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    private static Stream<String> containOnlyWhitespace() {
        return Stream.of(" ",
            "\u005Ct", // tab
            "\u005Cn", // line feed
            "\u005Cr", // carriage return
            " \u005Cn\u005Cr  ");
    }

    @ParameterizedTest
    @MethodSource("containOneOrMoreWhitespaces")
    void shouldNotReturnFailureWhenActualContainsOneOrMoreWhitespaces(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsWhitespaces();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    private static Stream<String> containOneOrMoreWhitespaces() {
        return Stream.of("a ",
            "a b",
            "a  b",
            "a\u005Ctb", // tab
            "a\u005Cnb", // line feed
            "a\u005Crb", // carriage return
            "a \u005Cn\u005Cr  b");
    }

    @ParameterizedTest
    @MethodSource("doesNotContainAnyWhitespaces")
    void shouldReturnFailureWhenActualDoesNotContainAnyWhitespaces(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsWhitespaces();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    private static Stream<String> doesNotContainAnyWhitespaces() {
        return Stream.of(null,
            "",
            "a",
            "bc");
    }

}
