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

public class IsAfterOrEqualDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Supplier<Date> other;
    private final ChronoUnit truncateTo;

    public IsAfterOrEqualDateConstraint(Date other) {
        this(Suppliers.create(other), null);
    }

    public IsAfterOrEqualDateConstraint(Date other, ChronoUnit truncateTo) {
        this(Suppliers.create(other), truncateTo);
    }

    public IsAfterOrEqualDateConstraint(Supplier<Date> other) {
        this(other, null);
    }

    public IsAfterOrEqualDateConstraint(Supplier<Date> other, ChronoUnit truncateTo) {
        super(DefaultMessages.TIME_IS_AFTER_OR_EQUAL);
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
        return !value.before(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, Date> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
