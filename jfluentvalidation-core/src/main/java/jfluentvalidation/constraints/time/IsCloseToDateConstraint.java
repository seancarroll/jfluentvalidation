package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.util.Date;

import static java.lang.Math.abs;

public class IsCloseToDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Date other;
    private final long offsetValue;
    private final boolean strict;

    // offsetValue - millis
    public IsCloseToDateConstraint(@Nonnull Date other, long offsetValue, boolean strict) {
        super(DefaultMessages.IS_CLOSE_TO);
        this.other = Ensure.notNull(other);
        this.offsetValue = offsetValue;
        this.strict = strict;
    }

    @Override
    public boolean isValid(RuleContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        long absDiff = absDiff(context.getPropertyValue(), other);
        if (strict) {
            return absDiff < offsetValue;
        }

        return absDiff <= offsetValue;
    }

    private static long absDiff(Date first, Date second) {
        return abs(first.getTime() - second.getTime());
    }

    // TODO: consistent messages between Date/Calendar/Temporal isCloseTo? what about numbers?
    @Override
    public void addParametersToContext(RuleContext<T, Date> context) {
        context.getMessageContext().appendArgument("other", other);
        context.getMessageContext().appendArgument("offsetValue", offsetValue);
    }
}
