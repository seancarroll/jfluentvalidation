package validators.rulefor.uri;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPortTest {

    @Test
    void shouldReturnFailureIfActualUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPort(8080);

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnNoFailureWhenActualUriHasTheGivenPort() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:8080"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPort(8080);

        List<ValidationFailure> failures = validator.validate(m);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUriPortIsNotTheGivenPort() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com:80"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPort(8080);

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

}
