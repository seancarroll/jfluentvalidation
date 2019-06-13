package validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.url.Profile;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HasPathTest {

    // should_pass_if_actual_url_has_the_given_path
    @Test
    void shouldNotReturnFailureWhenGivenPathIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    // should_fail_if_actual_is_null
    @Test
    void shouldReturnFailureWhenUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    // should_throw_an_exception_fail_if_given_path_is_null
    @Test
    void shouldThrowAnExceptionWhenGivenPathIsNull() {
        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);

        assertThrows(NullPointerException.class, () -> validator.ruleForUrl(Profile::getWebsite).hasPath(null));
    }

    // should_fail_if_actual_URL_path_is_not_the_given_path
    @Test
    void shouldReturnFailureWhenActualPathIsNotTheGivenPath() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/blogs"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    // should_fail_if_actual_URL_has_no_path_and_the_given_path_is_not_null
    @Test
    void shouldReturnFailureWhenActualPathIsNotPresentAndGivenPathIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

}
