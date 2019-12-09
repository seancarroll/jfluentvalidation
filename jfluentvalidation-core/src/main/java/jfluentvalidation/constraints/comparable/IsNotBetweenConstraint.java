package jfluentvalidation.constraints.comparable;

import jfluentvalidation.common.Comparables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

/**
 *   * Asserts that two <code>{@link Comparable}</code>s are not equal by invoking
 *    * <code>{@link Comparable#compareTo(Object)}</code> .<br>
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotBetweenConstraint<T, P extends Comparable<? super P>> extends AbstractConstraint<T, P> {

    private final P start;
    private final P end;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    /**
     *
     * @param start
     * @param end
     * @param inclusiveStart
     * @param inclusiveEnd
     */
    public IsNotBetweenConstraint(@Nonnull P start, @Nonnull P end, boolean inclusiveStart, boolean inclusiveEnd) {
        super(DefaultMessages.COMPARABLE_IS_NOT_BETWEEN);
        this.start = Ensure.notNull(start);
        this.end = Ensure.notNull(end);
        Ensure.argument(start.compareTo(end) <= 0, "start must be less than or equal to end");
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !Comparables.isBetween(context.getPropertyValue(), start, end, inclusiveStart, inclusiveEnd);
    }

    @Override
    public void addParametersToContext(RuleContext<T, P> context) {
        context.getMessageContext().appendArgument("start", start);
        context.getMessageContext().appendArgument("end", end);
        context.getMessageContext().appendArgument("inclusiveStart", inclusiveStart);
        context.getMessageContext().appendArgument("inclusiveEnd", inclusiveEnd);
    }
}
