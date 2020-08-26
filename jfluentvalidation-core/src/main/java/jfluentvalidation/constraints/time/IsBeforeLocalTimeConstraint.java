package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.time.LocalTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalTimeConstraint<T> extends AbstractConstraint<T, LocalTime> {

    private final Supplier<LocalTime> other;

    public IsBeforeLocalTimeConstraint(LocalTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeLocalTimeConstraint(Supplier<LocalTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = other;
    }

    @Override
    public boolean isValid(ConstraintContext<T, LocalTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalTime otherValue = other.get();
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
