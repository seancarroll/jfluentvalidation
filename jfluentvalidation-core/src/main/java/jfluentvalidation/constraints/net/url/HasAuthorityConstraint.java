package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URL;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAuthorityConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasAuthorityConstraint(String expected) {
        super(DefaultMessages.NET_HAS_AUTHORITY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return Objects.equals(expected, context.getPropertyValue().getAuthority());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URL> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
