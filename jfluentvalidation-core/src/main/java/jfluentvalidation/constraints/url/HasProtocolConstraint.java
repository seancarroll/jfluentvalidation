package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasProtocolConstraint<T> implements Constraint<T, URL> {

    private final String expected;

    public HasProtocolConstraint(String expected) {
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        return expected.equals(context.getPropertyValue().getProtocol());
    }
}
