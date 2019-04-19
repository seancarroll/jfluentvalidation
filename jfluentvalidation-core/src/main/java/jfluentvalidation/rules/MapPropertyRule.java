package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

// TODO: should we extend from PropertyRule?

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class MapPropertyRule<T, K, V> extends PropertyRule<T, Map<K, V>> {

    private final Predicate<Map.Entry<K, V>> predicate;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;
    // TODO: make getter / setter
    public List<Constraint<?, Map.Entry<K, V>>> entryConstraints = new ArrayList<>();

    public MapPropertyRule(Function<T, Map<K, V>> propertyFunc, String propertyName) {
        this(propertyFunc, propertyName, null);
    }

    public MapPropertyRule(Function<T, Map<K, V>> propertyFunc, String propertyName, Predicate<Map.Entry<K, V>> predicate) {
        super(propertyFunc, propertyName);
        this.predicate = predicate;
    }

    // TODO: fix validate...how to handle constraints for entry / key / value?
    @Override
    public List<ValidationFailure> validate(ValidationContext<T, Map<K, V>> context) {
        List<ValidationFailure> failures = super.validate(context);

        Map<K, V> propertyValue = getPropertyFunc().apply(context.getInstanceToValidate());
        for (Map.Entry<K, V> entry : propertyValue.entrySet()) {

            if (predicate == null || predicate.test(entry)) {
                for (Constraint<?, Map.Entry<K, V>> entryConstraint : entryConstraints) {
                    ValidationContext childContext = new ValidationContext(context.getInstanceToValidate());
                    if (!entryConstraint.isValid(new RuleContext(childContext, this, entry))) {
                        String errorMessage = entryConstraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                        failures.add(new ValidationFailure(getPropertyName(), errorMessage, entry));
                    }
                }
            }

        }

        return failures;
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
