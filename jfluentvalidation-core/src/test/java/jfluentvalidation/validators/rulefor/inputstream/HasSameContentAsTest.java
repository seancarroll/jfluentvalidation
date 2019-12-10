package jfluentvalidation.validators.rulefor.inputstream;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasSameContentAsTest {

    @Test
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() {
        Target t = new Target(new ByteArrayInputStream(new byte[0]));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(new byte[0]));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(new byte[0]));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenNameIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(null));
    }


    @Test
    void shouldThrowExceptionWhenThereIsAnIOException() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read()).thenThrow(IOException.class);

        Target t = new Target(is);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(new byte[0]));

        assertThrows(UncheckedIOException.class, () -> validator.validate(t));
    }

    @Test
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() {
        Target t = new Target(new ByteArrayInputStream(new byte[0]));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(" ".getBytes()));

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
