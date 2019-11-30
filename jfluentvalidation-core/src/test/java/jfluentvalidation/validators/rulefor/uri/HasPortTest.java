package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPortTest {

    @Test
    void shouldNotReturnFailureWhenActualUriHasTheGivenPort() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPort(8080);

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfActualUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPort(8080);

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualUriPortIsNotTheGivenPort() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:80"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPort(8080);

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

}
