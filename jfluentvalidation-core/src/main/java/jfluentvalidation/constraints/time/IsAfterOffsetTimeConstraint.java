package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.time.OffsetTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOffsetTimeConstraint<T> extends AbstractConstraint<T, OffsetTime> {

    private final Supplier<OffsetTime> other;

    public IsAfterOffsetTimeConstraint(OffsetTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterOffsetTimeConstraint(Supplier<OffsetTime> other) {
        this(DefaultMessages.TIME_IS_AFTER, other);
    }

    IsAfterOffsetTimeConstraint(String errorMessage, Supplier<OffsetTime> other) {
        super(errorMessage);
        this.other = other;
    }

    @Override
    public boolean isValid(ConstraintContext<T, OffsetTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        OffsetTime otherValue = other.get();
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
