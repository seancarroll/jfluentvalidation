package validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasAnchorConstraintTest {

    @Test
    void shouldReturnFailureWhenActualAnchorIsNotPresentAndExpectedAnchorIsNotNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("something");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualAnchorDoesNotMatchExpectedAnchor() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/#something"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("other");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasNoAnchorAndExpectedIsNull() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor(null);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualUrlHasExpectedAnchor() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com/pages/#something"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("something");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasAnchor("pages");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }
}
