package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasHostConstraintTest {

    @Test
    void shouldReturnFailureWhenActualHostIsNotPresentAndExpectedIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("something");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualHostDoesNotMatchExpected() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("other.com");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasNoHostAndExpectedIsEmptyString() throws MalformedURLException {
        Profile p = new Profile(new URL("http://"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUrlHasNoHostAndExpectedIsNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost(null);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasExpectedHost() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("example.com");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

}
