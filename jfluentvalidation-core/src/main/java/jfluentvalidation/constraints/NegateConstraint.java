package jfluentvalidation.constraints;

import jfluentvalidation.validators.ConstraintContext;

// TODO: not sure how to handle error messages.
// this might not be a good idea and we might not even need it.
// we could make it abstract
public abstract class NegateConstraint<T, P> extends AbstractConstraint<T, P> {

    private final Constraint<T, P> innerConstraint;

    public NegateConstraint(Constraint<T, P> innerConstraint) {
        // TODO: if we did this what would the error message be?
        super(innerConstraint.getOptions().getErrorMessage());
        this.innerConstraint = innerConstraint;
    }

    @Override
    public boolean isValid(ConstraintContext<T, P> context) {
        return !innerConstraint.isValid(context);
    }
}
