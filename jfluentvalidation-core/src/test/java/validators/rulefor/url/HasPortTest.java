package validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.url.Profile;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPortTest {

    // should_fail_if_actual_is_null
    @Test
    void shouldReturnFailureIfActualIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPort(8080);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    // should_pass_if_actual_url_has_the_given_port
    @Test
    void shouldReturnNoFailureWhenActualUrlHasTheGivenPort() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com:8080"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPort(8080);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    // should_fail_if_actual_URL_port_is_not_the_given_port
    @Test
    void shouldReturnFailureWhenActualUrlPortIsNotTheGivenPort() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com:80"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPort(8080);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

}
