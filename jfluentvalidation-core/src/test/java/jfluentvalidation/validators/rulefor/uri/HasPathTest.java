package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPathTest {

    @Test
    void shouldNotReturnFailureWhenUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("pages");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenGivenPathIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com/pages"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("/pages");

        ValidationResult validationResult = validator.validate(m);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldThrowAnExceptionWhenGivenPathIsNull() {
        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);

        assertThrows(NullPointerException.class, () -> validator.ruleForUri(Media::getContentLocation).hasPath(null));
    }

    @Test
    void shouldReturnFailureWhenActualPathIsNotTheGivenPath() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com/blogs"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("pages");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualPathIsNotPresentAndGivenPathIsNotNull() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("pages");

        ValidationResult validationResult = validator.validate(m);

        assertFalse(validationResult.isValid());
    }

}
