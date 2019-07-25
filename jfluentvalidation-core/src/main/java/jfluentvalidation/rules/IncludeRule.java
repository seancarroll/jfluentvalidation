package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.ValidationContext;
import jfluentvalidation.validators.Validator;

import java.util.List;
import java.util.function.Function;

// TODO: should this extend PropertyRule? Its not really a property
/**
 *
 * @param <T>
 */
public class IncludeRule<T> extends PropertyRule<T, Object> {

    private final Validator<T> validator;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public IncludeRule(Validator<T> validator) {
        // TODO: not sure what to do about propertyname. Can it be null? What does FluentValidator do? ValidatorOptions PropertyNameResolver
        // should this really extends PropertyRule...seems like this is a poor choice
        this(v -> v, null, validator);
    }

    private IncludeRule(Function<T, Object> propertyFunc, String propertyName, Validator<T> validator) {
        super(propertyFunc, propertyName);
        this.validator = validator;
    }

    // TODO: fluentvalidation include rule extends property
    // return new IncludeRule(ctx => func((T)ctx.InstanceToValidate), cascadeModeThunk, typeof(T), typeof(T), typeof(TValidator));

    @Override
    public List<ValidationFailure> validate(ValidationContext<T> context) {
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

}
