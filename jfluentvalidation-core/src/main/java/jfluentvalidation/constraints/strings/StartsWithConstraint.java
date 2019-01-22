package jfluentvalidation.constraints.strings;

import jfluentvalidation.constraints.Constraint;

public class StartsWithConstraint implements Constraint<String> {

    private final String prefix;
    private final int offset;

    public StartsWithConstraint(String prefix) {
        this(prefix, 0);
    }

    public StartsWithConstraint(String prefix, int offset) {
        this.prefix = prefix;
        this.offset = offset;
    }

    @Override
    public boolean isValid(String instance) {
        // TODO: this probably should be based on a comparison strategy
        return instance.startsWith(prefix, offset);
    }
}
