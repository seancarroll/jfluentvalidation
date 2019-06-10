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
public class IsBeforeZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final ZonedDateTime other;

    public IsBeforeZonedDateTimeConstraint(ZonedDateTime other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        return context.getPropertyValue().isBefore(other);
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, ZonedDateTime> context) {
//        if (!context.getPropertyValue().isBefore(other)) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
