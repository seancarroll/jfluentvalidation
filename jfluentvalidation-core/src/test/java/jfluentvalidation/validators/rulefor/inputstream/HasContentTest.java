package jfluentvalidation.validators.rulefor.inputstream;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HasContentTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("some content");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenNameIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForInputStream(Target::getInputStream).hasContent(null));
    }


    @Test
    void shouldReturnFailureWhenThereIsAnIOException() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read()).thenThrow(IOException.class);

        Target t = new Target(is);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("stuff");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualFileContentMatchesGiven() throws FileNotFoundException {
        Target t = new Target(new FileInputStream("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("Some words and stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenFileDoesNotHaveExpectedContent() throws FileNotFoundException {
        Target t = new Target(new FileInputStream("src/test/resources/Test.txt"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForInputStream(Target::getInputStream).hasContent("Some words and other stuff.");

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


//    @Test
//    public void should_pass_if_inputstream_and_string_have_equal_content() throws IOException {
//        // GIVEN
//        given(diff.diff(actual, expected)).willReturn(emptyList());
//        // THEN
//        inputStreams.assertHasContent(someInfo(), actual, expectedString);
//    }
//
//    @Test
//    public void should_throw_error_wrapping_catched_IOException() throws IOException {
//        // GIVEN
//        IOException cause = new IOException();
//        given(diff.diff(actual, expectedString)).willThrow(cause);
//        // WHEN
//        Throwable error = catchThrowable(() -> inputStreams.assertHasContent(someInfo(), actual, expectedString));
//        // THEN
//        assertThat(error).isInstanceOf(InputStreamsException.class)
//            .hasCause(cause);
//    }
//
//    @Test
//    public void should_fail_if_inputstream_and_string_do_not_have_equal_content() throws IOException {
//        // GIVEN
//        List<Delta<String>> diffs = list((Delta<String>) mock(Delta.class));
//        given(diff.diff(actual, expectedString)).willReturn(diffs);
//        AssertionInfo info = someInfo();
//        // WHEN
//        catchThrowable(() -> inputStreams.assertHasContent(someInfo(), actual, expectedString));
//        // THEN
//        verify(failures).failure(info, shouldHaveSameContent(actual, expectedString, diffs));
//    }
}
