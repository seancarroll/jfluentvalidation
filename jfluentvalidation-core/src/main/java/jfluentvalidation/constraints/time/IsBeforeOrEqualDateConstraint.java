package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Dates;
import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Supplier;

public class IsBeforeOrEqualDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Supplier<Date> other;
    private final ChronoUnit truncateTo;

    public IsBeforeOrEqualDateConstraint(Date other) {
        this(Suppliers.create(other), null);
    }

    public IsBeforeOrEqualDateConstraint(Date other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsBeforeOrEqualDateConstraint(Supplier<Date> other) {
        this(other, null);
    }

    public IsBeforeOrEqualDateConstraint(Supplier<Date> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
        this.truncateTo = truncateTo;
    }

    @Override
    public boolean isValid(RuleContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Date value = context.getPropertyValue();
        if (truncateTo != null) {
            value = Dates.truncateTo(value, truncateTo);
        }

        Date otherValue = other.get();
        boolean isBeforeOrEqual = !value.after(otherValue);
        if (!isBeforeOrEqual) {
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isBeforeOrEqual;
    }

}
