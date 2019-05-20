package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected authority.
 * @param <T>  the target type supported by an implementation.
 */
public class HasAuthorityConstraint<T> implements Constraint<T, URI> {

    private final String expected;

    public HasAuthorityConstraint(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        return context.getPropertyValue().getAuthority().equals(expected);
    }
}
