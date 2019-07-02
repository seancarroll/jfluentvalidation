package jfluentvalidation.validators.rulefor.inputstream;

import jfluentvalidation.IORuntimeException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasSameContentAsTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(new byte[0]));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
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

        assertThrows(IORuntimeException.class, () -> validator.validate(t));
    }

    @Test
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() {
        Target t = new Target(new ByteArrayInputStream(new byte[0]));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(new byte[0]));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() {
        Target t = new Target(new ByteArrayInputStream(new byte[0]));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasSameContentAs(new ByteArrayInputStream(" ".getBytes()));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
