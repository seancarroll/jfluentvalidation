package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsPatternTest {

    private static final String MATCH_ANYTHING_REGEX = ".*";

    @Test
    void shouldNotReturnFailureWhenActualContainsRegEx() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsPattern("sleep");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsPattern() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsPattern(Pattern.compile("sleep"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsPattern(MATCH_ANYTHING_REGEX);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    // TODO: include in java doc
//    @throws NullPointerException if the given pattern is {@code null}.
//    @throws PatternSyntaxException if the regular expression's syntax is invalid.

    @Test
    void shouldThrowExceptionWhenRegExIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).containsPattern((String) null));
    }

    @Test
    void shouldThrowExceptionWhenPatternIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).containsPattern((Pattern) null));
    }

    @Test
    void shouldThrowExceptionWhenRegExSyntaxIsInvalid() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(PatternSyntaxException.class, () -> validator.ruleForString(Target::getValue).containsPattern("*"));
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainRegEx() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsPattern("hello");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainPattern() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).containsPattern(Pattern.compile("hello"));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
