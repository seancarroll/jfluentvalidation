package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

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
    public boolean isValid(ConstraintContext<T, P> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().compareTo(other) != 0;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, P> context) {
        context.getMessageContext().appendArgument("value", other);
    }
}
