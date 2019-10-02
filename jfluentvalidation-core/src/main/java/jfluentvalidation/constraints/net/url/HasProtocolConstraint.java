package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.net.URL;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasProtocolConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasProtocolConstraint(@Nonnull String expected) {
        super(DefaultMessages.URL_HAS_PROTOCOL);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return expected.equals(context.getPropertyValue().getProtocol());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }


//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return expected.equals(context.getPropertyValue().getProtocol())
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,DEFAULT_MESSAGE));
//    }

//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (expected.equals(context.getPropertyValue().getProtocol())) {
//            addConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//
//    }


}
