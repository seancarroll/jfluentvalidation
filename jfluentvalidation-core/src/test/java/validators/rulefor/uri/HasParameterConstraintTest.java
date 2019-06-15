package validators.rulefor.uri;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasParameterConstraintTest {

    @Test
    void shouldReturnFailureWhenParametersAreNotPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenParameterIsNotPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("baz");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenExpectedParameterIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com?foo=bar"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasParameter("foo");

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

}
