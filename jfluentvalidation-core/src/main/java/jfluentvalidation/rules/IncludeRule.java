package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.ValidationContext;
import jfluentvalidation.validators.Validator;

import java.util.List;
import java.util.function.Predicate;

// TODO: should this extend PropertyRule?
/**
 *
 * @param <T>
 * @param <P>
 */
public class IncludeRule<T, P> implements Rule<T, P> {

    private final Validator<T> validator;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    /**
     *
     * @param validator
     */
    public IncludeRule(Validator<T> validator) {
        this.validator = validator;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        return validator.validate(context);
    }

    @Override
    public List<String> getRuleSet() {
        return ruleSet;
    }

    @Override
    public void setRuleSet(List<String> ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public void applyCondition(Predicate<T> predicate) {
        // TODO: implement
        throw new RuntimeException("applyCondition is not implemented");
    }

}
