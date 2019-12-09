package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

// TODO: is this the correct way of doing this?

/**
 *   * Asserts that two <code>{@link Comparable}</code>s are not equal by invoking
 *    * <code>{@link Comparable#compareTo(Object)}</code> .<br>
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsEqualAccordingToCompareToConstraint<T, P extends Comparable<? super P>> extends AbstractConstraint<T, P> {

    private final P other;

    public IsEqualAccordingToCompareToConstraint(@Nonnull P other) {
        super(DefaultMessages.COMPARABLE_IS_EQUAL_ACCORDING_TO_COMPARE_TO);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().compareTo(other) == 0;
    }

    @Override
    public void addParametersToContext(RuleContext<T, P> context) {
        context.getMessageContext().appendArgument("value", other);
    }
}
