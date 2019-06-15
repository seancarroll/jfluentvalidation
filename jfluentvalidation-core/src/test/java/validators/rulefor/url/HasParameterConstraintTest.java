package validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HasParameterConstraintTest {

    @Test
    void shouldReturnFailureWhenParametersAreNotPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasParameter("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenParameterIsNotPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasParameter("baz");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnNoFailureWhenExpectedParameterIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasParameter("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

}
