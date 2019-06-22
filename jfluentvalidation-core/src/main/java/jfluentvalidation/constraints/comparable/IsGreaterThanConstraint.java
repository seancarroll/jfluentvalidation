package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;

/**
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsGreaterThanConstraint<T, P extends Comparable<? super P>> extends AbstractConstraint<T, P> {

    private final P other;

    public IsGreaterThanConstraint(@Nonnull P other) {
        super(DefaultMessages.COMPARABLE_IS_GREATER_THAN);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().compareTo(other) > 0;
    }
}
