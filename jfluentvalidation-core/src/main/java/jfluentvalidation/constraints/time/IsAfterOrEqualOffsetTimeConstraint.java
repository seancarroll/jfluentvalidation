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
public class IsAfterOrEqualOffsetTimeConstraint<T> extends AbstractConstraint<T, OffsetTime> {

    private final OffsetTime other;

    public IsAfterOrEqualOffsetTimeConstraint(OffsetTime other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetTime> context) {
        return !context.getPropertyValue().isBefore(other);
    }

//    @Override
//    protected void validate(RuleContext<T, OffsetTime> context) {
//        if (context.getPropertyValue().isBefore(other)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
