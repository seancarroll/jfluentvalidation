package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasNoQueryTest {

    @Test
    void shouldNotReturnFailureIfActualUrlDoesNotHaveQuery() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoQuery();

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfActualUrlIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoQuery();

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfActualUrlHasQuery() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasNoQuery();

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

}
