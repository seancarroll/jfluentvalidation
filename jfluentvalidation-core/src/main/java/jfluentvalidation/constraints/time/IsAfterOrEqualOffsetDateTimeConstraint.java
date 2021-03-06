package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualOffsetDateTimeConstraint<T> extends AbstractConstraint<T, OffsetDateTime> {

    private final Supplier<OffsetDateTime> other;
    private final ChronoUnit truncateTo;

    public IsAfterOrEqualOffsetDateTimeConstraint(OffsetDateTime other) {
        this(Suppliers.create(other), null);
    }

    public IsAfterOrEqualOffsetDateTimeConstraint(OffsetDateTime other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsAfterOrEqualOffsetDateTimeConstraint(Supplier<OffsetDateTime> other) {
        this(other, null);
    }

    public IsAfterOrEqualOffsetDateTimeConstraint(Supplier<OffsetDateTime> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = other;
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(ConstraintContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        OffsetDateTime value = context.getPropertyValue();
        if (truncateTo != null) {
            value = value.truncatedTo(truncateTo);
        }

        OffsetDateTime otherValue = other.get();
        boolean isAfterOrEqual = !value.isBefore(otherValue);
        if (!isAfterOrEqual) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", otherValue);
        }
        return isAfterOrEqual;
    }

}
