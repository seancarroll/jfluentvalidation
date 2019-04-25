package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

/**
 *
 * @param <T>
 * @param <P>
 */
public class IsNotBetweenConstraint<T, P extends Comparable<? super P>> implements Constraint<T, P> {
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
        this.start = start;
        this.end = end;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return false;
    }

}
