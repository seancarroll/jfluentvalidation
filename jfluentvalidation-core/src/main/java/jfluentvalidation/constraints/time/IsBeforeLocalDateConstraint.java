package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDate;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final LocalDate other;

    public IsBeforeLocalDateConstraint(LocalDate other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().isBefore(other);
    }

//    @Override
//    protected void validate(RuleContext<T, LocalDate> context) {
//        if (!context.getPropertyValue().isBefore(other)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
