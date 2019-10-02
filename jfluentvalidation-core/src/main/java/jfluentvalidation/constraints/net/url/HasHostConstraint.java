package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasHostConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasHostConstraint(String expected) {
        super(DefaultMessages.NET_HAS_HOST);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return Objects.equals(expected, context.getPropertyValue().getHost());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return context.getPropertyValue().getHost().equals(expected)
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasHost.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (!context.getPropertyValue().getHost().equals(expected)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

}
