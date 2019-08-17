package jfluentvalidation.validators.rulefor.inputstream;

import jfluentvalidation.IORuntimeException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasContentTest {

    @Test
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() throws FileNotFoundException {
        Target t = new Target(new FileInputStream("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("Some words and stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("some content");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenNameIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInputStream(Target::getInputStream).hasContent(null));
    }


    @Test
    void shouldThrowExceptionWhenThereIsAnIOException() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read()).thenThrow(IOException.class);

        Target t = new Target(is);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("stuff");

        assertThrows(IORuntimeException.class, () -> validator.validate(t));
    }

    @Test
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() throws FileNotFoundException {
        Target t = new Target(new FileInputStream("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("Some words and other stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
