package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPathTest {

    @Test
    void shouldNotReturnFailureWhenGivenPathIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowAnExceptionWhenGivenPathIsNull() {
        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);

        assertThrows(NullPointerException.class, () -> validator.ruleForUrl(Profile::getWebsite).hasPath(null));
    }

    @Test
    void shouldReturnFailureWhenActualPathIsNotTheGivenPath() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/blogs"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualPathIsNotPresentAndGivenPathIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

}
