package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasAuthorityTest {

    @Test
    void shouldNotReturnFailureWhenActualUriHasExpectedAuthority() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8080");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUriWithPathHasExpectedAuthority() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080/info"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8080");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("pages");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualAuthorityDoesNotMatchExpected() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("other.com");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenPortsDiffer() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8081");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8081");

        ValidationResult validationResult = validator.validate(m);

        assertEquals("contentLocation must have authority example.com:8081.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
