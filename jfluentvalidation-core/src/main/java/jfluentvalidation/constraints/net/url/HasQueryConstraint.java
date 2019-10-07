package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasQueryConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasQueryConstraint(String expected) {
        super(DefaultMessages.NET_HAS_QUERY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return Objects.equals(expected, context.getPropertyValue().getQuery());
    }

    @Override
    public void addParametersToContext(RuleContext<T, URL> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
