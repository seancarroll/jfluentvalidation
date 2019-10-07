package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final Supplier<LocalDateTime> other;

    public IsBeforeLocalDateTimeConstraint(LocalDateTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeLocalDateTimeConstraint(Supplier<LocalDateTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, LocalDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
