package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected port.
 * @param <T>  the target type supported by an implementation.
 */
public class HasPortConstraint<T> implements Constraint<T, URI> {

    private final int expected;

    public HasPortConstraint(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        return context.getPropertyValue().getPort() == expected;
    }
}
