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
public class IsBeforeOrEqualZonedDateTimeConstraint<T> extends AbstractConstraint<T, ZonedDateTime> {

    private final Supplier<ZonedDateTime> other;

    public IsBeforeOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this(Suppliers.create(other));
    }

    public IsBeforeOrEqualZonedDateTimeConstraint(Supplier<ZonedDateTime> other) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().isAfter(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, ZonedDateTime> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
