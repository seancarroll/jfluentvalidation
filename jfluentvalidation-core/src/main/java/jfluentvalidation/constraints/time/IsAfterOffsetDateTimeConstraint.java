package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOffsetDateTimeConstraint<T> extends AbstractConstraint<T, OffsetDateTime> {

    private final Supplier<OffsetDateTime> other;

    public IsAfterOffsetDateTimeConstraint(OffsetDateTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterOffsetDateTimeConstraint(Supplier<OffsetDateTime> other) {
        this(DefaultMessages.TIME_IS_AFTER, other);
    }

    IsAfterOffsetDateTimeConstraint(String errorMessage, Supplier<OffsetDateTime> other) {
        super(errorMessage);
        this.other = other;
    }

    @Override
    public boolean isValid(ConstraintContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        OffsetDateTime otherValue = other.get();
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
