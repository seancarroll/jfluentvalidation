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
public class IsAfterCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Calendar other;

    public IsAfterCalendarConstraint(Calendar other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().after(other);
    }

//    @Override
//    protected void validate(RuleContext<T, Calendar> context) {
//        if (!context.getPropertyValue().after(other)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
