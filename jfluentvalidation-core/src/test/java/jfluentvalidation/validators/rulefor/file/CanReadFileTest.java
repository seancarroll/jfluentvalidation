package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CanReadFileTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).canRead();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureIfCanRead() {
        File actual = mock(File.class);
        when(actual.canRead()).thenReturn(true);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).canRead();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }


    @Test
    void shouldReturnFailureIfCannotRead() {
        File actual = mock(File.class);
        when(actual.canRead()).thenReturn(false);

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).canRead();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
