package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URI;
import java.util.Objects;

/**
 * Verifies that the actual {@code URI} has the expected host.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasHostConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasHostConstraint(String expected) {
        super(DefaultMessages.NET_HAS_HOST);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return Objects.equals(expected, context.getPropertyValue().getHost());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URI> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
