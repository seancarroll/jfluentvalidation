package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasNoPathTest {

    @Test
    void shouldNotReturnFailureWhenPathIsNotPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoPath();

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenPathIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoPath();

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

}
