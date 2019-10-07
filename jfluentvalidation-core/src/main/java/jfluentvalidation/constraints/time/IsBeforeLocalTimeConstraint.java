package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalTimeConstraint<T> extends AbstractConstraint<T, LocalTime> {

    private final Supplier<LocalTime> other;

    public IsBeforeLocalTimeConstraint(LocalTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeLocalTimeConstraint(Supplier<LocalTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, LocalTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
