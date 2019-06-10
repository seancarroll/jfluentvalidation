package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.net.URI;

// TODO: better name?

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPathConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasPathConstraint(@Nonnull String expected) {
        super(DefaultMessages.HAS_PATH);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        return expected.equals(context.getPropertyValue().getPath());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, URI> context) {
//        if (!expected.equals(context.getPropertyValue().getPath())) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
