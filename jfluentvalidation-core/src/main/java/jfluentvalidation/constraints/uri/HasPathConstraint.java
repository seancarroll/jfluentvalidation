package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.net.URI;

// TODO: better name?

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPathConstraint<T> implements Constraint<T, URI> {

    private final String expected;

    public HasPathConstraint(@Nonnull String expected) {
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        return expected.equals(context.getPropertyValue().getPath());
    }
}
