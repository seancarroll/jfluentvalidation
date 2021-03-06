package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Dates;
import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;
import jfluentvalidation.validators.ValidatorOptions;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOrEqualCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Supplier<Calendar> other;
    private final ChronoUnit truncateTo;

    public IsBeforeOrEqualCalendarConstraint(Calendar other) {
        this(Suppliers.create(other), null);
    }

    public IsBeforeOrEqualCalendarConstraint(Calendar other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsBeforeOrEqualCalendarConstraint(Supplier<Calendar> other) {
        this(other, null);
    }

    public IsBeforeOrEqualCalendarConstraint(Supplier<Calendar> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(ConstraintContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Calendar value = context.getPropertyValue();
        if (truncateTo != null) {
            value = Dates.truncateTo(value, truncateTo);
        }

        Calendar otherValue = other.get();
        boolean isBeforeOrEqual = !value.after(otherValue);
        if (!isBeforeOrEqual) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", ValidatorOptions.format(otherValue));
        }

        return isBeforeOrEqual;
    }
}
