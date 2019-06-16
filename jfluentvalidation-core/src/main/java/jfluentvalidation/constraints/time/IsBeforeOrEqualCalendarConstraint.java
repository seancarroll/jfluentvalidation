package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Calendar;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOrEqualCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Calendar other;

    public IsBeforeOrEqualCalendarConstraint(Calendar other) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return !context.getPropertyValue().after(other);
    }

//    @Override
//    protected void validate(RuleContext<T, Calendar> context) {
//        if (context.getPropertyValue().after(other)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
