package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasNoParameterConstraintTest {

    @Test
    void shouldNotReturnFailureWhenActualUriHasNoParameters() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters();

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithoutValueIsMissing() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsMissing() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo", "bar");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUriWhenAtLeastOneParameterIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters();

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUriHasExpectedParameter() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualURIDoesNotHaveExpectedParameter() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("blah");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterIsPresentWithoutValue() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterIsPresentWithValue() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterAppearsMultipleTimes() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar&foo=blah"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterWithoutValueIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsPresentWithoutValue() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo", "bar");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsPresentWithDifferentValue() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo", "baz");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterWithValueIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoParameters("foo", "bar");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }
}
