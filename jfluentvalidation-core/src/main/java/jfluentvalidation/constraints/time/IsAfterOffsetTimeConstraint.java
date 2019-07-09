package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOffsetTimeConstraint<T> extends AbstractConstraint<T, OffsetTime> {

    private final OffsetTime other;

    public IsAfterOffsetTimeConstraint(OffsetTime other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isAfter(other);
    }

//    @Override
//    protected void validate(RuleContext<T, OffsetTime> context) {
//        if (!context.getPropertyValue().isAfter(other)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
