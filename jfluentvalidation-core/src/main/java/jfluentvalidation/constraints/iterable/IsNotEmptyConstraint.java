package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code Iterable} being validated is not empty.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotEmptyConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    public IsNotEmptyConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return !Iterables.isEmpty(context.getPropertyValue());
    }
}
