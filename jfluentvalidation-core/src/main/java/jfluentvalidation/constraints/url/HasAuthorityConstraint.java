package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAuthorityConstraint<T> implements Constraint<T, URL> {

    private final String expected;

    public HasAuthorityConstraint(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        return context.getPropertyValue().getAuthority().equals(expected);
    }
}
