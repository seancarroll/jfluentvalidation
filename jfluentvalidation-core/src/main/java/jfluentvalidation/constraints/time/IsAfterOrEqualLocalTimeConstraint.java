package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualLocalTimeConstraint<T> extends AbstractConstraint<T, LocalTime> {

    private final Supplier<LocalTime> other;

    public IsAfterOrEqualLocalTimeConstraint(LocalTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterOrEqualLocalTimeConstraint(Supplier<LocalTime> other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().isBefore(other.get());
    }

//    @Override
//    protected void validate(RuleContext<T, LocalTime> context) {
//        if (context.getPropertyValue().isBefore(other)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
