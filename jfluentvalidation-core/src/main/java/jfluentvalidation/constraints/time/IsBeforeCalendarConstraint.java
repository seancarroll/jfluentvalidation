package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidatorOptions;

import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Supplier<Calendar> other;

    public IsBeforeCalendarConstraint(Calendar other) {
        this(Suppliers.create(other));
    }

    public IsBeforeCalendarConstraint(Supplier<Calendar> other) {
        this(DefaultMessages.TIME_IS_BEFORE, other);
    }

    IsBeforeCalendarConstraint(String errorMessage, Supplier<Calendar> other) {
        super(errorMessage);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Calendar otherValue = other.get();
        boolean isBefore = context.getPropertyValue().before(otherValue);
        if (!isBefore) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", ValidatorOptions.format(otherValue));
        }

        return isBefore;
    }
}
