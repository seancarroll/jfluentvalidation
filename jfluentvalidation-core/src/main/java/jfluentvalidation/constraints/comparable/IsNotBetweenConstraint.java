package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

import javax.annotation.Nonnull;

public class IsNotBetweenConstraint<T extends Comparable<T>> implements Constraint<T> {
    private final T start;
    private final T end;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    public IsNotBetweenConstraint(@Nonnull T start, @Nonnull T end, boolean inclusiveStart, boolean inclusiveEnd) {
        this.start = start;
        this.end = end;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(T value) {
        return false;
    }

}
