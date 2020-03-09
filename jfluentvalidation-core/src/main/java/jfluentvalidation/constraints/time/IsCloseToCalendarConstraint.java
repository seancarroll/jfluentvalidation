package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.util.Calendar;

import static jfluentvalidation.common.Dates.absDiff;

/**
 * Constraint that the actual value is close to the expected one by less than the given offset.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsCloseToCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Calendar other;
    private final long offsetValue;
    private final boolean strict;

    // offsetValue - millis
    // TODO: can we use a value with an appropriate temporal?
    public IsCloseToCalendarConstraint(@Nonnull Calendar other, long offsetValue, boolean strict) {
        super(DefaultMessages.IS_CLOSE_TO);
        this.other = Ensure.notNull(other);
        this.offsetValue = offsetValue;
        this.strict = strict;
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        long absDiff = absDiff(context.getPropertyValue(), other);
        if (strict) {
            return absDiff < offsetValue;
        }

        return absDiff <= offsetValue;
    }

    // TODO: consistent messages between Date/Calendar/Temporal isCloseTo? what about numbers?
    @Override
    public void addParametersToContext(RuleContext<T, Calendar> context) {
        context.getMessageContext().appendArgument("other", other);
        context.getMessageContext().appendArgument("offsetValue", offsetValue);
        context.getMessageContext().appendArgument("strict", strict);
    }
}
