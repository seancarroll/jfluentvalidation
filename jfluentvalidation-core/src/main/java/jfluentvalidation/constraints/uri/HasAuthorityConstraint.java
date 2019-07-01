package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

/**
 * Verifies that the actual {@code URI} has the expected authority.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAuthorityConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasAuthorityConstraint(String expected) {
        super(DefaultMessages.HAS_AUTHORITY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().getAuthority().equals(expected);
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, URI> context) {
//        if (!context.getPropertyValue().getAuthority().equals(expected)) {
//            addConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
