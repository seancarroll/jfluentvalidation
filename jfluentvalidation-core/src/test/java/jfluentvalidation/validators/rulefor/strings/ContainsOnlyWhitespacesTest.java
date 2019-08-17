package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsOnlyWhitespacesTest {

    @ParameterizedTest
    @MethodSource("containOnlyWhitespace")
    void shouldNotReturnFailureWhenActualOnlyContainsWhitespaces(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsOnlyWhitespaces();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    static Stream<String> containOnlyWhitespace() {
        return Stream.of(" ",
            "\u005Ct", // tab
            "\u005Cn", // line feed
            "\u005Cr", // carriage return
            " \u005Cn\u005Cr  ");
    }

    @ParameterizedTest
    @MethodSource("containNotOnlyWhitespace")
    void shouldReturnFailureWhenActualDoesNotContainOnlyWhitespaces(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsOnlyWhitespaces();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    static Stream<String> containNotOnlyWhitespace() {
        return Stream.of(null,
            "",
            "a",
            " bc ",
            "\u00A0", // non-breaking space
            "\u2007", // non-breaking space
            "\u202F"); // non-breaking space
    }

}
