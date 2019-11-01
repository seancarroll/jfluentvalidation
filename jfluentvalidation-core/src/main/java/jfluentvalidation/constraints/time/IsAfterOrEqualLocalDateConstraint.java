package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final Supplier<LocalDate> other;

    public IsAfterOrEqualLocalDateConstraint(LocalDate other) {
        this(Suppliers.create(other));
    }

    public IsAfterOrEqualLocalDateConstraint(Supplier<LocalDate> other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalDate otherValue = other.get();
        boolean isAfterOrEqual = !context.getPropertyValue().isBefore(otherValue);
        if (!isAfterOrEqual) {
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isAfterOrEqual;
    }
}
