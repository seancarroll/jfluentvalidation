package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final Supplier<LocalDate> other;

    public IsBeforeLocalDateConstraint(LocalDate other) {
        this(Suppliers.create(other));
    }

    public IsBeforeLocalDateConstraint(Supplier<LocalDate> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = other;
    }

    @Override
    public boolean isValid(ConstraintContext<T, LocalDate> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalDate otherValue = other.get();
        boolean isBefore = context.getPropertyValue().isBefore(otherValue);
        if (!isBefore) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isBefore;
    }
}
