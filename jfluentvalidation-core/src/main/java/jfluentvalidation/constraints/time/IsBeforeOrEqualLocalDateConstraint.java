package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOrEqualLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final Supplier<LocalDate> other;

    public IsBeforeOrEqualLocalDateConstraint(LocalDate other) {
        this(Suppliers.create(other));
    }

    public IsBeforeOrEqualLocalDateConstraint(Supplier<LocalDate> other) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalDate otherValue = other.get();
        boolean isBeforeOrEqual = !context.getPropertyValue().isAfter(otherValue);
        if (!isBeforeOrEqual) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isBeforeOrEqual;
    }
}
