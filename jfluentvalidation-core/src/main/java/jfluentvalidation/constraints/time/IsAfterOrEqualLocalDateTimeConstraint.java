package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final Supplier<LocalDateTime> other;

    public IsAfterOrEqualLocalDateTimeConstraint(LocalDateTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterOrEqualLocalDateTimeConstraint(Supplier<LocalDateTime> other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, LocalDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
