package jfluentvalidation.validators.rulefor.uri;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasHostConstraintTest {

    @Test
    void shouldReturnFailureWhenActualHostDoesNotMatchExpected() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasHost("other.com");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUriHasExpectedHost() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasHost("example.com");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasHost("pages");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

}
