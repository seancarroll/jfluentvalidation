package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class StartsWithConstraint implements Constraint<CharSequence> {

    private final CharSequence prefix;
    private final int offset;

    public StartsWithConstraint(CharSequence prefix) {
        this(prefix, 0);
    }

    public StartsWithConstraint(CharSequence prefix, int offset) {
        this.prefix = Ensure.notNull(prefix);
        this.offset = Ensure.nonnegative(offset, "offset");
    }

    @Override
    public boolean isValid(CharSequence value) {
        // TODO: this probably should be based on a comparison strategy
        return value.toString().startsWith(prefix.toString(), offset);
    }
}
