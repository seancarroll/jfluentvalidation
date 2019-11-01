package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualOffsetTimeConstraint<T> extends AbstractConstraint<T, OffsetTime> {

    private final Supplier<OffsetTime> other;

    public IsAfterOrEqualOffsetTimeConstraint(OffsetTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterOrEqualOffsetTimeConstraint(Supplier<OffsetTime> other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        OffsetTime otherValue = other.get();
        boolean isAfterOrEqual = !context.getPropertyValue().isBefore(otherValue);
        if (!isAfterOrEqual) {
            context.getMessageContext().appendArgument("other", otherValue);
        }
        return isAfterOrEqual;
    }
}
