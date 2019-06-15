package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasQueryConstraint<T> extends AbstractConstraint<T, URI> {

    private final String expected;

    public HasQueryConstraint(String expected) {
        super(DefaultMessages.HAS_QUERY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return Objects.equals(expected, context.getPropertyValue().getQuery());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }


//    @Override
//    protected void validate(RuleContext<T, URI> context) {
//        if (!expected.equals(context.getPropertyValue().getQuery())) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
