package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAuthorityConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasAuthorityConstraint(String expected) {
        super(DefaultMessages.HAS_AUTHORITY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return Objects.equals(expected, context.getPropertyValue().getAuthority());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return context.getPropertyValue().getAuthority().equals(expected)
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasAuthority.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (!context.getPropertyValue().getAuthority().equals(expected)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

}
