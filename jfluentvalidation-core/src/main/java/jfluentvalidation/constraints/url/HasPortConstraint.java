package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPortConstraint<T> extends AbstractConstraint<T, URL> {

    private final int expected;

    public HasPortConstraint(int expected) {
        super(DefaultMessages.HAS_PORT);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
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
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return context.getPropertyValue().getPort() == expected
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasPort.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (context.getPropertyValue().getPort() != expected) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }
}
