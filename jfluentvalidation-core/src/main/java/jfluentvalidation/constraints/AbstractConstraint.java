package jfluentvalidation.constraints;

import jfluentvalidation.validators.ConstraintOptions;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

// TODO: does this make sense to have?
// Lets see how things start coming together and if we need this
public abstract class AbstractConstraint<T, P> implements Constraint<T, P> {

    protected final ConstraintOptions options = new ConstraintOptions();
    private final List<ConstraintViolation> constraintViolations = new ArrayList<>();

    public AbstractConstraint(String errorMessage) {
        options.setErrorMessage(errorMessage);
    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, P> context) {
//        validate(context);
//
//        return constraintViolations;
//    }

    // protected abstract void validate(RuleContext<T, P> context);

    protected void addConstraint(ConstraintViolation violation) {
        constraintViolations.add(violation);
    }

    public List<ConstraintViolation> getConstraintViolations() {
        return constraintViolations;
    }

    @Override
    public ConstraintOptions getOptions() {
        return options;
    }

    @Override
    public void addParametersToContext(RuleContext<T, P> context) {
        // default do nothing
    }
}
