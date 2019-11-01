package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final Supplier<ZonedDateTime> other;

    public IsAfterZonedDateTimeConstraint(ZonedDateTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterZonedDateTimeConstraint(Supplier<ZonedDateTime> other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        ZonedDateTime otherValue = other.get();
        boolean isAfter = context.getPropertyValue().isAfter(otherValue);
        if (!isAfter) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isAfter;
    }

}
