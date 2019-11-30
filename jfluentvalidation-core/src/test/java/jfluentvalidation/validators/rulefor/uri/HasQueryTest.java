package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasQueryTest {

    @Test
    void shouldNotReturnFailureIfActualUriHasExpectedQuery() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasQuery("foo=bar");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfActualUriHasNoQueryAndGivenIsNull() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasQuery(null);

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfActualUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasQuery("foo=bar");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfActualUriQueryDoesNotMatchExpected() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasQuery("foo=baz");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfActualUriHasNoQueryAndExpectedIsNotNull() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasQuery("foo=bar");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfActualUriHasQueryAndGivenIsNull() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasQuery(null);

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }
}
