package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

public class IsLessThanConstraint<T, P extends Comparable<P>> implements Constraint<T, P> {

    private final P other;

    public IsLessThanConstraint(@Nonnull P other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return false;
    }
}
