package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsBlankTest {

    @ParameterizedTest
    @MethodSource("blank")
    void shouldNotReturnFailureWhenActualIsBlank(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isBlank();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    private static Stream<String> blank() {
        return Stream.of(
            null,
            "",
            " ",
            "\u005Ct", // tab
            "\u005Cn", // line feed
            "\u005Cr", // carriage return
            " \u005Cn\u005Cr  ");
    }

    @ParameterizedTest
    @MethodSource("notBlank")
    void shouldReturnFailureWhenActualIsNotBlank(String actual) {
        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).isBlank();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    private static Stream<String> notBlank() {
        return Stream.of(
            "a",
            " b ",
            "\u00A0", // non-breaking space
            "\u2007", // non-breaking space
            "\u202F"); // non-breaking space
    }

}
