package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasExtensionTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("txt");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
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

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @ParameterizedTest()
    @ValueSource(strings = {"file.txt", "some.file.txt"})
    void shouldNotReturnFailureWhenActualHasExpectedExtension(String name) {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn(name);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("txt");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualDoesNotHaveExpectedExtension() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn("file.txt");

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasExtension("pdf");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
