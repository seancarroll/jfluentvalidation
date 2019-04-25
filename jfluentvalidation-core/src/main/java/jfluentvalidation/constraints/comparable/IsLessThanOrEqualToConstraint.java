package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

public class IsLessThanOrEqualToConstraint<T, P extends Comparable<? super P>> implements Constraint<T, P> {

    private final P other;

    public IsLessThanOrEqualToConstraint(@Nonnull P other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue().compareTo(other) <= 0;
    }
}
