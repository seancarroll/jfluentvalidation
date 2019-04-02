package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.RunnableValidator;
import jfluentvalidation.validators.ValidationContext;
import jfluentvalidation.validators.Validator;

import java.util.List;

public class RuleSetRule<T, P> implements Rule<T, P> {

    private final Validator<T> validator;
    private List<String> ruleSet;

    public RuleSetRule(Class<T> clazz, List<String> ruleSet, Runnable runnable) {
        // TODO: this fails with attempting to resolve type
        this.validator = new RunnableValidator<>(clazz, runnable);
        this.ruleSet = ruleSet;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext context) {
        return validator.validate(context, ruleSet);
    }

    @Override
    public List<String> getRuleSet() {
        return ruleSet;
    }

    @Override
    public void setRuleSet(List<String> ruleSet) {
        this.ruleSet = ruleSet;
    }
}
