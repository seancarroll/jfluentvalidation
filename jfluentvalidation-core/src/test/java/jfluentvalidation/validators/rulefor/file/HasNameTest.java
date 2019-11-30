package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasNameTest {

    @Test
    void shouldNotReturnFailureWhenActualHasExpectedName() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn("tmp.txt");

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasName("tmp.txt");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasName("tmp");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenNameIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFile(Target::getFile).hasName(null));
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotHaveExpectedName() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn("tmp.txt");

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasName("tmp2.txt");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
