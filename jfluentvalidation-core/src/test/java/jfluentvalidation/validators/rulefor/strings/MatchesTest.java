package jfluentvalidation.validators.rulefor.strings;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchesTest {

    private static final String MATCH_ANYTHING_REGEX = ".*";

    @Test
    void shouldNotReturnFailureWhenActualContainsRegEx() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).matches(".+sleep.*");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualContainsPattern() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).matches(Pattern.compile(".+sleep.*"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).matches(MATCH_ANYTHING_REGEX);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    // TODO: include in java doc
//    @throws NullPointerException if the given pattern is {@code null}.
//    @throws PatternSyntaxException if the regular expression's syntax is invalid.


    @Test
    void shouldThrowExceptionWhenRegExIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).matches((String) null));
    }

    @Test
    void shouldThrowExceptionWhenPatternIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForString(Target::getValue).matches((Pattern) null));
    }

    @Test
    void shouldThrowExceptionWhenRegExSyntaxIsInvalid() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(PatternSyntaxException.class, () -> validator.ruleForString(Target::getValue).matches("*"));
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainRegEx() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).matches("hello");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotContainPattern() {
        Target t = new Target("oh boy, sleep! That's where I'm a viking!");

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForString(Target::getValue).matches(Pattern.compile("hello"));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
