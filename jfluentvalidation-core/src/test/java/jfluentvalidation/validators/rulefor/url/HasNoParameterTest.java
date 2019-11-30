package jfluentvalidation.validators.rulefor.url;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasNoParameterTest {

    @Test
    void shouldNotReturnFailureWhenActualUrlHasNoParameters() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters();

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithoutValueIsMissing() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsMissing() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "bar");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualUrlWhenAtLeastOneParameterIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters();

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualUrlHasExpectedParameter() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlDoesNotHaveExpectedParameter() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("blah");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfParameterIsPresentWithoutValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfParameterIsPresentWithValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfParameterAppearsMultipleTimes() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar&foo=blah"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfParameterWithoutValueIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsPresentWithoutValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "bar");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsPresentWithDifferentValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "baz");

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureIfParameterWithValueIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "bar");

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }
}
