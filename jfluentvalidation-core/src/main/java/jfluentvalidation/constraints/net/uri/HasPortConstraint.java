package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected port.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPortConstraint<T> extends AbstractConstraint<T, URI> {

    private final int expected;

    public HasPortConstraint(int expected) {
        super(DefaultMessages.NET_HAS_PORT);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getPort() == expected;
    }

    @Override
    public void addParametersToContext(RuleContext<T, URI> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
