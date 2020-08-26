package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Dates;
import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;
import jfluentvalidation.validators.ValidatorOptions;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Supplier<Calendar> other;
    private final ChronoUnit truncateTo;

    public IsAfterOrEqualCalendarConstraint(Calendar other) {
        this(Suppliers.create(other), null);
    }

    public IsAfterOrEqualCalendarConstraint(Calendar other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsAfterOrEqualCalendarConstraint(Supplier<Calendar> other) {
        this(other, null);
    }

    public IsAfterOrEqualCalendarConstraint(Supplier<Calendar> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = other;
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
        boolean isAfterOrEqual = !value.before(otherValue);
        if (!isAfterOrEqual) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", ValidatorOptions.format(otherValue));
        }

        return isAfterOrEqual;
    }
}
