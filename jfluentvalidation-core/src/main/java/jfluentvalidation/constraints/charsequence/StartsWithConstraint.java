package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class StartsWithConstraint implements Constraint<CharSequence> {

    private final CharSequence prefix;
    private final int offset;

    public StartsWithConstraint(CharSequence prefix) {
        this(prefix, 0);
    }

    public StartsWithConstraint(CharSequence prefix, int offset) {
        this.prefix = prefix;
        this.offset = offset;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        // TODO: this probably should be based on a comparison strategy
        return instance.toString().startsWith(prefix.toString(), offset);
    }
}
