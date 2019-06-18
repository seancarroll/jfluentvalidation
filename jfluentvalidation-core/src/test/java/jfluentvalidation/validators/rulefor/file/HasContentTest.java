package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasContentTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("stuff");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenNameIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFile(Target::getFile).hasContent(null));
    }

    @Test
    void shouldThrowExceptionWhenGivenCharsetIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForFile(Target::getFile).hasContent("some text", null));
    }

    @Test
    void shouldReturnFailureWhenActualIsNotAFile() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(false);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("stuff");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and other stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenThereIsAnIOException() {
        Target t = new Target(new File("src/test/resources/a_fake_file.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("stuff");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
