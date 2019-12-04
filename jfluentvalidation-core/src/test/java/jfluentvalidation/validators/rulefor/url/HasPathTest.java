package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasPathTest {

    @Test
    void shouldNotReturnFailureWhenUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenGivenPathIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("/pages");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
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

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualPathIsNotPresentAndGivenPathIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldHaveAppropriateErrorMessage() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasPath("pages");

        ValidationResult validationResult = validator.validate(p);

        assertEquals("website must have path pages.", validationResult.getViolations().get(0).getErrorMessage());
    }
}
