package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected authority.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAuthorityConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasAuthorityConstraint(String expected) {
        super(DefaultMessages.NET_HAS_AUTHORITY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getAuthority().equals(expected);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URI> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
