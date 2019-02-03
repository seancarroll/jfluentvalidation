package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsNotBetweenConstraint implements Constraint<Comparable<?>> {
    private final Comparable start;
    private final Comparable end;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    public IsNotBetweenConstraint(Comparable start, Comparable end, boolean inclusiveStart, boolean inclusiveEnd) {
        this.start = start;
        this.end = end;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(Comparable instance) {
        return false;
    }

}
