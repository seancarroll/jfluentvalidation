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
public class IsBeforeOrEqualLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final LocalDate other;

    public IsBeforeOrEqualLocalDateConstraint(LocalDate other) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        return !context.getPropertyValue().isAfter(other);
    }

//    @Override
//    protected void validate(RuleContext<T, LocalDate> context) {
//        if (context.getPropertyValue().isAfter(other)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
