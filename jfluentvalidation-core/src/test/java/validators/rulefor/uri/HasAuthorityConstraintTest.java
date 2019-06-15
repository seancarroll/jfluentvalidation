package validators.rulefor.uri;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasAuthorityConstraintTest {

    @Test
    void shouldReturnFailureWhenActualUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("pages");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenActualUriHasExpectedAuthority() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8080");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUriWithPathHasExpectedAuthority() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080/info"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8080");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualAuthorityDoesNotMatchExpected() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("other.com");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenPortsDiffer() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasAuthority("example.com:8081");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

}
