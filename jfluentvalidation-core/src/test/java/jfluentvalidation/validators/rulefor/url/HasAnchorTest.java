package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasAnchorTest {

    @Test
    void shouldNotReturnFailureWhenActualUrlHasNoAnchorAndExpectedIsNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor(null);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasExpectedAnchor() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/#something"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("something");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("pages");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualAnchorIsNotPresentAndExpectedAnchorIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("something");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualAnchorDoesNotMatchExpectedAnchor() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/#something"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("other");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }
}
