package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasParameterTest {

    @Test
    void shouldReturnFailureWhenParametersAreNotPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("foo");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenParameterIsNotPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("baz");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenParameterValueDoesNotMatch() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("foo", "baz");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenExpectedParameterIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("foo");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenExpectedParameterHasValue() throws URISyntaxException {
        Media p = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("foo", "bar");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }
}
