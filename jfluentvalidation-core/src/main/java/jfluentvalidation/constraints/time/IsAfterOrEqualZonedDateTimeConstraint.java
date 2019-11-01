package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final Supplier<ZonedDateTime> other;
    private final ChronoUnit truncateTo;

    public IsAfterOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this(Suppliers.create(other), null);
    }

    public IsAfterOrEqualZonedDateTimeConstraint(ZonedDateTime other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsAfterOrEqualZonedDateTimeConstraint(Supplier<ZonedDateTime> other) {
        this(other, null);
    }

    public IsAfterOrEqualZonedDateTimeConstraint(Supplier<ZonedDateTime> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = other;
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        ZonedDateTime value = context.getPropertyValue();
        if (truncateTo != null) {
            value = value.truncatedTo(truncateTo);
        }

        ZonedDateTime otherValue = other.get();
        boolean isAfterOrEqual = !value.isBefore(otherValue);
        if (!isAfterOrEqual) {
            context.getMessageContext().appendArgument("other", otherValue);
        }
        return isAfterOrEqual;
    }

}
