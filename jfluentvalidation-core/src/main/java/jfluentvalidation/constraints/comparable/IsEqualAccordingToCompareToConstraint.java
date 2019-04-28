package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

// TODO: is this the correct way of doing this?

/**
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsEqualAccordingToCompareToConstraint<T, P extends Comparable<? super P>> implements Constraint<T, P> {

    private final P other;

    public IsEqualAccordingToCompareToConstraint(@Nonnull P other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue().compareTo(other) == 0;
    }
}
