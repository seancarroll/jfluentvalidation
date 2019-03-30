package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.ValidationContext;
import jfluentvalidation.validators.Validator;

import java.util.List;

/**
 *
 * @param <T>
 * @param <P>
 */
public class IncludeRule<T, P> implements Rule<T, P> {

    private final Validator<T> validator;
    private final List<String> ruleSet = RuleSet.DEFAULT_LIST;

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
    public List<String> getRuleset() {
        return ruleSet;
    }

    // TODO: hmmm...not sure what to do here. do we need this if we are passing the subject via the context?
//    @Override
//    public Subject<?, P> getSubject() {
//        return null;
//    }
}
