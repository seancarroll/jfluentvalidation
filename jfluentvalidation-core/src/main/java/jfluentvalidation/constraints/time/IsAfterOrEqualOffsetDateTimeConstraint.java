package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

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
        this.other = Ensure.notNull(other);
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        OffsetDateTime value = context.getPropertyValue();
        if (truncateTo != null) {
            value = value.truncatedTo(truncateTo);
        }
        return !value.isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, OffsetDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
