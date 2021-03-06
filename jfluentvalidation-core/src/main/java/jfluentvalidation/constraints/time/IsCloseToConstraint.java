package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

import static java.lang.Math.abs;

/**
 * Constraint that the actual value is close to the expected one by less than the given offset.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsCloseToConstraint<T, P extends Temporal> extends AbstractConstraint<T, P> {

    private final P other;
    private final long offsetValue;
    private final TemporalUnit offsetUnit;
    private final boolean strict;

    public IsCloseToConstraint(@Nonnull P other, long offsetValue, @Nonnull TemporalUnit offsetUnit, boolean strict) {
        super(DefaultMessages.IS_CLOSE_TO);
        this.other = Ensure.notNull(other);
        this.offsetValue = offsetValue;
        this.offsetUnit = Ensure.notNull(offsetUnit);
        this.strict = strict;
        if (!other.isSupported(offsetUnit)) {
            throw new UnsupportedTemporalTypeException("Unsupported unit: " + offsetUnit);
        }
    }

    @Override
    public boolean isValid(ConstraintContext<T, P> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        long absDiff = absDiff(context.getPropertyValue(), other);
        if (strict) {
            return absDiff < offsetValue;
        }

        return absDiff <= offsetValue;
    }

    private long absDiff(P first, P second) {
        return abs(offsetUnit.between(first, second));
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, P> context) {
        context.getMessageContext().appendArgument("other", other);
        context.getMessageContext().appendArgument("offsetValue", offsetValue);
        context.getMessageContext().appendArgument("offsetUnit", offsetUnit);
        context.getMessageContext().appendArgument("strict", strict);
    }
}
