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

class HasNoParameterConstraintTest {

    @Test
    void shouldNotReturnFailureWhenActualUrlHasNoParameters() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters();

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithoutValueIsMissing() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsMissing() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "bar");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUrlWhenAtLeastOneParameterIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters();

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUrlHasExpectedParameter() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlDoesNotHaveExpectedParameter() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("blah");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterIsPresentWithoutValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterIsPresentWithValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterAppearsMultipleTimes() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar&foo=blah"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterWithoutValueIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsPresentWithoutValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "bar");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfParameterWithValueIsPresentWithDifferentValue() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "baz");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureIfParameterWithValueIsPresent() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com?foo=bar"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasNoParameters("foo", "bar");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }
}
