package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected port.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPortConstraint<T> extends AbstractConstraint<T, URI> {

    private final int expected;

    public HasPortConstraint(int expected) {
        super(DefaultMessages.HAS_PORT);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().getPort() == expected;
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, URI> context) {
//        if (context.getPropertyValue().getPort() != expected) {
//            addConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
