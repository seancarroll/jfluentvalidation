package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URL;
import java.util.Objects;

/**
 * Gets the anchor (also known as the "reference") of this {@code URL}.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAnchorConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasAnchorConstraint(String expected) {
        super(DefaultMessages.URL_HAS_ANCHOR);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return Objects.equals(expected, context.getPropertyValue().getRef());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URL> context) {
        context.getMessageContext().appendArgument("value", expected);
    }
}
