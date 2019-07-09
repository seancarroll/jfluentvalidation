package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalTimeConstraint<T> extends AbstractConstraint<T, LocalTime> {

    private final LocalTime other;

    public IsBeforeLocalTimeConstraint(LocalTime other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isBefore(other);
    }

//    @Override
//    protected void validate(RuleContext<T, LocalTime> context) {
//        if (!context.getPropertyValue().isBefore(other)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
