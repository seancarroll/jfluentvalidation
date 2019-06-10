package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected host.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasHostConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasHostConstraint(String expected) {
        super(DefaultMessages.HAS_HOST);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        return expected.equals(context.getPropertyValue().getHost());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, URI> context) {
//        if (!context.getPropertyValue().getHost().equals(expected)) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
