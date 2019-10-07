package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Supplier;

public class IsBeforeDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Supplier<Date> other;

    public IsBeforeDateConstraint(Date other) {
        this(Suppliers.create(other));
    }

    public IsBeforeDateConstraint(Supplier<Date> other) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().before(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, Date> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
