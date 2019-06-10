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
public class IsBeforeOrEqualLocalTimeConstraint<T> extends AbstractConstraint<T, LocalTime> {

    private final LocalTime other;

    public IsBeforeOrEqualLocalTimeConstraint(LocalTime other) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        return !context.getPropertyValue().isAfter(other);
    }


//    @Override
//    protected void validate(RuleContext<T, LocalTime> context) {
//        if (context.getPropertyValue().isAfter(other)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
