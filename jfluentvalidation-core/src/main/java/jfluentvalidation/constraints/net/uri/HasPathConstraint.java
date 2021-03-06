package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.net.URI;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPathConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasPathConstraint(@Nonnull String expected) {
        super(DefaultMessages.NET_HAS_PATH);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return expected.equals(context.getPropertyValue().getPath());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URI> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
