package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final Supplier<LocalDateTime> other;
    private final ChronoUnit truncateTo;

    public IsAfterOrEqualLocalDateTimeConstraint(LocalDateTime other) {
        this(Suppliers.create(other), null);
    }

    public IsAfterOrEqualLocalDateTimeConstraint(LocalDateTime other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsAfterOrEqualLocalDateTimeConstraint(Supplier<LocalDateTime> other) {
        this(other, null);
    }

    public IsAfterOrEqualLocalDateTimeConstraint(Supplier<LocalDateTime> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = Ensure.notNull(other);
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalDateTime value = context.getPropertyValue();
        if (truncateTo != null) {
            value = value.truncatedTo(truncateTo);
        }
        return !value.isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, LocalDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
