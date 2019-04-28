package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

import static jfluentvalidation.common.Comparables.isGreaterThan;
import static jfluentvalidation.common.Comparables.isLessThan;

// TODO: is this what we want?

/**
 *
 * @param <T>  type of instance to validate
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsBetweenConstraint<T, P extends Comparable<? super P>> implements Constraint<T, P> {

    private final P start;
    private final P end;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    // TODO: add other constructors?
    public IsBetweenConstraint(@Nonnull P start, @Nonnull P end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: check bounds
        this.start = Ensure.notNull(start);
        this.end = Ensure.notNull(end);
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        P actual = context.getPropertyValue();
        boolean checkLowerBoundaryRange = inclusiveStart ? !isGreaterThan(start, actual) : isLessThan(start, actual);
        boolean checkUpperBoundaryRange = inclusiveEnd ? !isGreaterThan(actual, end) : isLessThan(actual, end);

        return checkLowerBoundaryRange && checkUpperBoundaryRange;
    }

//    protected <T extends Comparable<? super T>> void checkBoundsValidity(T start, T end, boolean inclusiveStart, boolean inclusiveEnd) {
//        // don't use isLessThanOrEqualTo or isGreaterThanOrEqualTo to avoid equal comparison which makes BigDecimal
//        // to fail when start = end with different precision, ex: [10.0, 10.00].
//        boolean inclusiveBoundsCheck = inclusiveEnd && inclusiveStart && !isGreaterThan(start, end);
//        boolean strictBoundsCheck = !inclusiveEnd && !inclusiveStart && isLessThan(start, end);
//        String operator = inclusiveEnd && inclusiveStart ? "less than" : "less than or equal to";
//        String boundsCheckErrorMessage = format("The end value <%s> must not be %s the start value <%s>%s!", end, operator, start,
//            (comparisonStrategy.isStandard() ? "" : " (using " + comparisonStrategy + ")"));
//        checkArgument(inclusiveBoundsCheck || strictBoundsCheck, boundsCheckErrorMessage);
//    }

}
