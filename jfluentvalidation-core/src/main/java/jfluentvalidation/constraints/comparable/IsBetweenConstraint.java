package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsBetweenConstraint implements Constraint<Comparable> {

    private final Comparable start;
    private final Comparable end;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    public IsBetweenConstraint(Comparable start, Comparable end, boolean inclusiveStart, boolean inclusiveEnd) {
        this.start = start;
        this.end = end;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(Comparable instance) {
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
