package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URL;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPortConstraint<T> extends AbstractConstraint<T, URL> {

    private final int expected;

    public HasPortConstraint(int expected) {
        super(DefaultMessages.NET_HAS_PORT);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getPort() == expected;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URL> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
