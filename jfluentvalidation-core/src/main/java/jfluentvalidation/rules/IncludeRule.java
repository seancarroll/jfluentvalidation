package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.ValidationContext;
import jfluentvalidation.validators.Validator;

import java.util.List;
import java.util.function.Function;
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

    // TODO: fluentvalidation include rule extends property
    // return new IncludeRule(ctx => func((T)ctx.InstanceToValidate), cascadeModeThunk, typeof(T), typeof(T), typeof(TValidator));

    /**
     *
     * @param validator
     */
    public IncludeRule(Validator<T> validator) {
        // TODO: how to get subject?
        // super();
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

    // TODO: ugh! what to do here? given validator doesnt expose subject.
    // I guess I could expose subject however I have a feeling this issue is actually highlighting a problematic design
    // QUESTION: should Subject just be the way I build up rules?
    @Override
    public Function<Object, P> getPropertyFunc() {
        return null;
    }

    @Override
    public void applyCondition(Predicate<T> predicate) {
        // TODO: implement
        throw new RuntimeException("applyCondition is not implemented");
    }

}
