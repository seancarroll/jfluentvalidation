package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code Object} being validated is {@code null}.
 *
 * @param <T>  the target type supported by an implementation
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNullConstraint<T, P> extends AbstractConstraint<T, P> {

    public IsNullConstraint() {
        super(DefaultMessages.IS_NULL);
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue() == null;
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, P> context) {
//        if (context.getPropertyValue() == null) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }


}
