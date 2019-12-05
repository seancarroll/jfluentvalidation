package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.IORuntimeException;
import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasContentTest {

    @Test
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and stuff.");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }


    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("stuff");

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotAFile() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(false);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("stuff");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and other stuff.");

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
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

    @Test
    void shouldHaveAppropriateErrorMessage() {
        Target t = new Target(new File("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasContent("Some words and other stuff.");

        ValidationResult validationResult = validator.validate(t);

        assertEquals("file must have content 'Some words and other stuff.' with charset UTF-8.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
