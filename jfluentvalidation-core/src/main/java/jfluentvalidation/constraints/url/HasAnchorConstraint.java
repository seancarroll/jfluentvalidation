package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

/**
 * Gets the anchor (also known as the "reference") of this {@code URL}.
 * @param <T>  the target type supported by an implementation.
 */
public class HasAnchorConstraint<T> implements Constraint<T, URL> {

    private final String expected;

    public HasAnchorConstraint(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        return context.getPropertyValue().getRef().equals(expected);
    }
}
