package jfluentvalidation.constraints.comparable;

import jfluentvalidation.common.Comparables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

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
        return Comparables.isBetween(actual, start, end, inclusiveStart, inclusiveEnd);
    }

}
