package jfluentvalidation.constraints;

import jfluentvalidation.validators.ConstraintOptions;
import jfluentvalidation.validators.RuleContext;

public abstract class AbstractConstraint<T, P> implements Constraint<T, P> {

    // TODO: move error message to RuleContext or ConstraintOptions which can be retrieved from some context
    // Again RuleContext is probably better named ConstraintContext
    private final ConstraintOptions options = new ConstraintOptions();

    public AbstractConstraint(String errorMessage) {
        options.setErrorMessage(errorMessage);
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
