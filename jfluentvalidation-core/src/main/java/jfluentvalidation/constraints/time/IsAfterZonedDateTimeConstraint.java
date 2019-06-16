package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final ZonedDateTime other;

    public IsAfterZonedDateTimeConstraint(ZonedDateTime other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().isAfter(other);
    }

//    @Override
//    protected void validate(RuleContext<T, ZonedDateTime> context) {
//        if (!context.getPropertyValue().isAfter(other)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
