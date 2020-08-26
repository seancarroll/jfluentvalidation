package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.util.Date;

import static jfluentvalidation.common.Dates.absDiff;

public class IsNotCloseToDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Date other;
    private final long offsetValue;
    private final boolean strict;

    // offsetValue - millis
    public IsNotCloseToDateConstraint(@Nonnull Date other, long offsetValue, boolean strict) {
        super(DefaultMessages.IS_CLOSE_TO);
        this.other = Ensure.notNull(other);
        this.offsetValue = offsetValue;
        this.strict = strict;
    }

    @Override
    public boolean isValid(ConstraintContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        long absDiff = absDiff(context.getPropertyValue(), other);
        if (strict) {
            return absDiff >= offsetValue;
        }
        return absDiff > offsetValue;
    }

    // TODO: consistent messages between Date/Calendar/Temporal isCloseTo? what about numbers?
    @Override
    public void addParametersToContext(ConstraintContext<T, Date> context) {
        context.getMessageContext().appendArgument("other", other);
        context.getMessageContext().appendArgument("offsetValue", offsetValue);
        context.getMessageContext().appendArgument("strict", strict);
    }
}
