package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Date;
import java.util.function.Supplier;

public class IsAfterDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Supplier<Date> other;

    public IsAfterDateConstraint(Date other) {
        this(Suppliers.create(other));
    }

    public IsAfterDateConstraint(Supplier<Date> other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Date otherValue = other.get();
        boolean isAfter = context.getPropertyValue().after(otherValue);
        if (!isAfter) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isAfter;
    }
}
