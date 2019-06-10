package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotEqualAccordingToCompareToConstraint<T, P extends Comparable<? super P>> extends AbstractConstraint<T, P> {

    private final P other;

    public IsNotEqualAccordingToCompareToConstraint(P other) {
        super(DefaultMessages.COMPARABLE_IS_NOT_EQUAL_ACCORDING_TO_COMPARE_TO);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue().compareTo(other) != 0;
    }
}
