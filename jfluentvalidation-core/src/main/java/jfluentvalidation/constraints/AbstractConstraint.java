package jfluentvalidation.constraints;

import jfluentvalidation.validators.ConstraintOptions;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConstraint<T, P> implements Constraint<T, P> {

    private final ConstraintOptions options = new ConstraintOptions();
    private final List<ConstraintViolation> constraintViolations = new ArrayList<>();

    public AbstractConstraint(String errorMessage) {
        options.setErrorMessage(errorMessage);
    }

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
