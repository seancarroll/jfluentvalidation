package jfluentvalidation.validators.rulefor.file;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasNameTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasName("tmp");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
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

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualHasExpectedName() {
        File actual = mock(File.class);
        when(actual.isFile()).thenReturn(true);
        when(actual.getName()).thenReturn("tmp.txt");

        Target t = new Target(actual);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForFile(Target::getFile).hasName("tmp.txt");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    // TODO: send PR to fix name
//    @Test
//    public void should_pass_if_actual_has_expected_extension() {
//        when(actual.getName()).thenReturn(expectedName);
//        files.assertHasName(someInfo(), actual, expectedName);
//    }
}
