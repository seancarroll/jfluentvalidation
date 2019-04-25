package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

// TODO: is this what we want?
public class IsBetweenConstraint<T, P extends Comparable<? super P>> implements Constraint<T, P> {

    private final P start;
    private final P end;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    // TODO: add other constructors?
    public IsBetweenConstraint(@Nonnull P start, @Nonnull P end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: check not null
        // TODO: check bounds
        this.start = start;
        this.end = end;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return false;
    }

//    assertNotNull(info, actual);
//    checkNotNull(start, "The start range to compare actual with should not be null");
//    checkNotNull(end, "The end range to compare actual with should not be null");
//    checkBoundsValidity(start, end, inclusiveStart, inclusiveEnd);
//    boolean checkLowerBoundaryRange = inclusiveStart ? !isGreaterThan(start, actual) : isLessThan(start, actual);
//    boolean checkUpperBoundaryRange = inclusiveEnd ? !isGreaterThan(actual, end) : isLessThan(actual, end);
//    if (checkLowerBoundaryRange && checkUpperBoundaryRange)
//        return;
//    throw failures.failure(info, shouldBeBetween(actual, start, end, inclusiveStart, inclusiveEnd, comparisonStrategy));

}
