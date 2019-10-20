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
public class IsBeforeOrEqualLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final Supplier<LocalDateTime> other;
    private final ChronoUnit truncateTo;

    public IsBeforeOrEqualLocalDateTimeConstraint(LocalDateTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeOrEqualLocalDateTimeConstraint(LocalDateTime other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsBeforeOrEqualLocalDateTimeConstraint(Supplier<LocalDateTime> other) {
        this(other, null);
    }

    public IsBeforeOrEqualLocalDateTimeConstraint(Supplier<LocalDateTime> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
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
        return !value.isAfter(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, LocalDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
