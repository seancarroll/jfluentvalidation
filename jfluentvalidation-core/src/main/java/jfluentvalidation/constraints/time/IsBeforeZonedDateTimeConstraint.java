package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final Supplier<ZonedDateTime> other;

    public IsBeforeZonedDateTimeConstraint(ZonedDateTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeZonedDateTimeConstraint(Supplier<ZonedDateTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        ZonedDateTime otherValue = other.get();
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
