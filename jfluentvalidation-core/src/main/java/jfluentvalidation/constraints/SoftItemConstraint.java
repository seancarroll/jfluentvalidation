package jfluentvalidation.constraints;

import jfluentvalidation.validators.ConstraintContext;

import java.util.function.Predicate;

// TODO: is there a way to merge this with SoftConstraint?
// This is actually a soft property constrain while the other is a soft type constraint
// Is there a way to pass in a predicate including the test that should run?
public class SoftItemConstraint<T, P> extends AbstractConstraint<T, P> {

    private final Predicate<? super P> condition;
    private final Constraint<T, P> innerConstraint;

    public SoftItemConstraint(Predicate<? super P> condition, Constraint<T, P> innerConstraint) {
        super(innerConstraint.getOptions().getErrorMessage());
        this.condition = condition;
        this.innerConstraint = innerConstraint;
    }

    @Override
    public boolean isValid(ConstraintContext<T, P> context) {
        if (condition.test(context.getPropertyValue())) {
            return innerConstraint.isValid(context);
        }

        return true;
    }

}
