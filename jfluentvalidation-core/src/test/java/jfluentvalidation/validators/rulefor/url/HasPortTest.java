package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPortTest {

    @Test
    void shouldNotReturnFailureWhenActualUrlHasTheGivenPort() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com:8080"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPort(8080);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfActualUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPort(8080);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualUrlPortIsNotTheGivenPort() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com:80"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPort(8080);

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

}
