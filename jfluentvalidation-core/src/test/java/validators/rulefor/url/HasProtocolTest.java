package validators.rulefor.url;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HasProtocolTest {

    @Test
    void shouldReturnFailureIfActualUrlIsNull() {
        Profile p = new Profile(null);

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasProtocol("http");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureIfActualUrlHasExpectedProtocol() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasProtocol("http");

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualProtocolDoesNotMatchExpected() throws MalformedURLException {
        Profile p = new Profile(new URL("http://example.com"));

        DefaultValidator<Profile> validator = new DefaultValidator<>(Profile.class);
        validator.ruleForUrl(Profile::getWebsite).hasProtocol("ftp");

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }
}
