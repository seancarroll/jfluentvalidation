package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetDateTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOffsetDateTimeConstraint<T> extends AbstractConstraint<T, OffsetDateTime> {

    private final OffsetDateTime other;

    public IsBeforeOffsetDateTimeConstraint(OffsetDateTime other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().isBefore(other);
    }

//    @Override
//    protected void validate(RuleContext<T, OffsetDateTime> context) {
//        if (!context.getPropertyValue().isBefore(other)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
