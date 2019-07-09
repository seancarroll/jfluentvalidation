package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final LocalDateTime other;

    public IsBeforeLocalDateTimeConstraint(LocalDateTime other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isBefore(other);
    }

//    @Override
//    protected void validate(RuleContext<T, LocalDateTime> context) {
//        if (!context.getPropertyValue().isBefore(other)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
