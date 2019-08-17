package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.IORuntimeException;
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
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("stuff");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
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
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and other stuff.");

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
    void shouldThrowExceptionWhenThereIsAnIOException() {
        // Mocks dont work here so toggle readable on a real file
        File file = new File("src/test/resources/UnreadableFile.txt");
        file.setReadable(false);

        try {
            Target t = new Target(file);

            DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
            validator.ruleForFile(Target::getFile).hasContent("stuff");

            assertThrows(IORuntimeException.class, () -> validator.validate(t));
        } finally {
            // things such as git dont like it when the file is not readable so put it back into a readable state
            file.setReadable(true);
        }
    }
}
