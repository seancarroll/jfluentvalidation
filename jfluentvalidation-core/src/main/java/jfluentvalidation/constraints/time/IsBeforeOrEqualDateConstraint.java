package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Date;
import java.util.function.Supplier;

public class IsBeforeOrEqualDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Supplier<Date> other;

    public IsBeforeOrEqualDateConstraint(Date other) {
        this(Suppliers.create(other));
    }

    public IsBeforeOrEqualDateConstraint(Supplier<Date> other) {
        super(DefaultMessages.TIME_IS_BEFORE_OR_EQUAL);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().after(other.get());
    }

}
