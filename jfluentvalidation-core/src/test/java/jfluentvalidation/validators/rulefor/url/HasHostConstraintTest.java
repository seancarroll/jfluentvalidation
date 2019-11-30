package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasHostConstraintTest {

    @Test
    void shouldReturnFailureWhenActualHostIsNotPresentAndExpectedIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("something");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualHostDoesNotMatchExpected() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("other.com");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasNoHostAndExpectedIsEmptyString() throws MalformedURLException {
        Profile p = new Profile(new URL("http://"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("pages");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualUrlHasNoHostAndExpectedIsNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost(null);

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasExpectedHost() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasHost("example.com");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

}
