package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasExtensionTest {

    @ParameterizedTest()
    @ValueSource(strings = {"file.txt", "some.file.txt"})
    void shouldNotReturnFailureWhenActualHasExpectedExtension(String name) {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn(name);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("txt");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("txt");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenExtensionIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFile(Target::getFile).hasExtension(null));
    }

    // TODO: do we want to throw?. Include is directory in error message.
    @Test
    void shouldReturnFailureWhenActualIsNotAFile() {
        File actual = mock(File.class);
        when(actual.isDirectory()).thenReturn(true);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("txt");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotHaveExpectedExtension() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn("file.txt");

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("pdf");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn("file.txt");

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("pdf");

        ValidationResult validationResult = validator.validate(t);

        assertEquals("file must have extension pdf.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
