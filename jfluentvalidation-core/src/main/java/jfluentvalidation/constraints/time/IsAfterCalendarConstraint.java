package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    // TODO: this format should come from a global option so that users can override
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final Supplier<Calendar> other;

    public IsAfterCalendarConstraint(Calendar other) {
        this(Suppliers.create(other));
    }

    public IsAfterCalendarConstraint(Supplier<Calendar> other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Calendar otherValue = other.get();
        boolean isAfter = context.getPropertyValue().after(otherValue);
        if (!isAfter) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", FORMAT.format(otherValue.getTime()));
        }

        return isAfter;
    }

}
