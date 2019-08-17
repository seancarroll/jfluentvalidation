package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Comparables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;

// TODO: Could I do something like the following?
// Seems like it will work. Whats the cost of the cast in Array.getLength?
// Given ArrayExactLengthConstraint doesnt use this how about we change the name to something like ArrayRangeLengthConstraint?
public class ArrayLengthConstraint<T, A> extends AbstractConstraint<T, A> {

    private final int min;
    private final int max;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    public ArrayLengthConstraint(int min, int max) {
        this(min, max, true, true);
    }

    public ArrayLengthConstraint(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        super(DefaultMessages.ARRAY_LENGTH);
        Ensure.argument(min <= max);
        this.min = min;
        this.max = max;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = Array.getLength(context.getPropertyValue());
        return Comparables.isBetween(len, min, max, inclusiveStart, inclusiveEnd);
    }

}

