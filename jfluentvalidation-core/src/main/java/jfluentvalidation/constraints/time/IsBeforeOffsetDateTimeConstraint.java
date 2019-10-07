package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOffsetDateTimeConstraint<T> extends AbstractConstraint<T, OffsetDateTime> {

    private final Supplier<OffsetDateTime> other;

    public IsBeforeOffsetDateTimeConstraint(OffsetDateTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeOffsetDateTimeConstraint(Supplier<OffsetDateTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, OffsetDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
