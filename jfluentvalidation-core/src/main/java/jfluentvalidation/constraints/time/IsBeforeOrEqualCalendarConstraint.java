package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Dates;
import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeOrEqualCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Supplier<Calendar> other;
    private final ChronoUnit truncateTo;

    public IsBeforeOrEqualCalendarConstraint(Calendar other) {
        this(Suppliers.create(other), null);
    }

    public IsBeforeOrEqualCalendarConstraint(Calendar other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsBeforeOrEqualCalendarConstraint(Supplier<Calendar> other) {
        this(other, null);
    }

    public IsBeforeOrEqualCalendarConstraint(Supplier<Calendar> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Calendar value = context.getPropertyValue();
        if (truncateTo != null) {
            value = Dates.truncateTo(value, truncateTo);
        }
        return !value.after(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, Calendar> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
