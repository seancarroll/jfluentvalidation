package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotEqualAccordingToCompareToConstraint<T, P extends Comparable<? super P>> implements Constraint<T, P> {

    private final P other;

    public IsNotEqualAccordingToCompareToConstraint(P other) {
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue().compareTo(other) != 0;
    }
}
