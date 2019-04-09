package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class IsNotEqualAccordingToCompareToConstraint<T, P extends Comparable<P>> implements Constraint<T, P> {

    private final P other;

    public IsNotEqualAccordingToCompareToConstraint(P other) {
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue().compareTo(other) != 0;
    }
}
