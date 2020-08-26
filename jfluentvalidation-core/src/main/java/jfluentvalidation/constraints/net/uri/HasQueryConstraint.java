package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URI;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasQueryConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasQueryConstraint(String expected) {
        super(DefaultMessages.NET_HAS_QUERY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return Objects.equals(expected, context.getPropertyValue().getQuery());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URI> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
