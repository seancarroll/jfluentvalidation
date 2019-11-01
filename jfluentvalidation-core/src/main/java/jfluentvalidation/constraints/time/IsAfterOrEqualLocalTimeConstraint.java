package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
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
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalTime otherValue = other.get();
        boolean isAfterOrEqual = !context.getPropertyValue().isBefore(otherValue);
        if (!isAfterOrEqual) {
            context.getMessageContext().appendArgument("other", otherValue);
        }
        return isAfterOrEqual;
    }
}
