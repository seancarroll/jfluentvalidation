package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOffsetTimeConstraint<T> extends AbstractConstraint<T, OffsetTime> {

    private final Supplier<OffsetTime> other;

    public IsBeforeOffsetTimeConstraint(OffsetTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeOffsetTimeConstraint(Supplier<OffsetTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, OffsetTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
