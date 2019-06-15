package validators.rulefor.uri;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HasPathTest {

    @Test
    void shouldNotReturnFailureWhenGivenPathIsPresent() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com/pages"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenUriIsNull() {
        Media m = new Media(null);

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
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

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualPathIsNotPresentAndGivenPathIsNotNull() throws URISyntaxException {
        Media m = new Media(new URI("http://example.com"));

        DefaultValidator<Media> validator = new DefaultValidator<>(Media.class);
        validator.ruleForUri(Media::getContentLocation).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(m);

        assertFalse(failures.isEmpty());
    }

}
