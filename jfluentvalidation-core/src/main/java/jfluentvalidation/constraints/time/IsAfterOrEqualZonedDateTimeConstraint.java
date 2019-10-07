package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final Supplier<ZonedDateTime> other;

    public IsAfterOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterOrEqualZonedDateTimeConstraint(Supplier<ZonedDateTime> other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().isBefore(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, ZonedDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
