package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Supplier<Calendar> other;

    public IsAfterOrEqualCalendarConstraint(Calendar other) {
        this(Suppliers.create(other));
    }

    public IsAfterOrEqualCalendarConstraint(Supplier<Calendar> other) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().before(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, Calendar> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
