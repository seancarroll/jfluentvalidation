package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

import static jfluentvalidation.common.Comparables.isGreaterThan;
import static jfluentvalidation.common.Comparables.isLessThan;

/**
 *
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
        // TODO: check not null
        // TODO: check bounds
        this.start = start;
        this.end = end;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        P actual = context.getPropertyValue();

        boolean checkLowerBoundaryRange = inclusiveStart ? !isGreaterThan(actual, start) : isLessThan(actual, start);
        boolean checkUpperBoundaryRange = inclusiveEnd ? !isGreaterThan(end, actual) : isLessThan(end, actual);

        return checkLowerBoundaryRange && checkUpperBoundaryRange;
    }

}
