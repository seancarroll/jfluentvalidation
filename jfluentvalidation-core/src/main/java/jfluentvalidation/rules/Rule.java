package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.ValidationContext;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @param <T> the type to validate
 * @param <P> the property type
 */
public interface Rule<T, P> {

    /**
     *
     * @param context
     * @return
     */
    List<ValidationFailure> validate(ValidationContext<T, P> context);

    /**
     * Name of the rule-set to which this rule belongs.
     * @return
     */
    List<String> getRuleSet();

    void setRuleSet(List<String> ruleSet);

    // TODO: maybe this isnt here but on PropertyRule and I update ValidationContext to use PropertyRule
    /**
     *
     * @return
     */
    Function<T, P> getPropertyFunc();

    String getPropertyName();

    // TODO: add ApplyConditionTo applyConditionTo = ApplyConditionTo.AllValidators???
    /**
     * Applies a condition to the rule
     * @param predicate
     */
    void applyCondition(Predicate<T> predicate);

    // void addConstraint(Constraint<T, P> constraint);

}
