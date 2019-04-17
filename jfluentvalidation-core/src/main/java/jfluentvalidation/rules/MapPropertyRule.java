package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
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

//    private final Subject<?, Map<K, V>> subject;
    private final Predicate<Map.Entry<K, V>> predicate;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;
    // TODO: make getter / setter
    public List<Constraint<?, Map.Entry<K, V>>> entryConstraints = new ArrayList<>();

//    /**
//     *
//     */
//    public MapPropertyRule(Subject<?, Map<K, V>> subject) {
//        this(subject, null);
//    }
//
//    /**
//     * @param predicate
//     */
//    public MapPropertyRule(Subject<?, Map<K, V>> subject, Predicate<Map.Entry<K, V>> predicate) {
//        this.subject = subject;
//        this.predicate = predicate;
//    }

    public MapPropertyRule(Function<T, Map<K, V>> propertyFunc, String propertyName) {
        this(propertyFunc, propertyName, null);
    }

    public MapPropertyRule(Function<T, Map<K, V>> propertyFunc, String propertyName, Predicate<Map.Entry<K, V>> predicate) {
        super(propertyFunc, propertyName);
        this.predicate = predicate;
    }

    // TODO: fix validate
    @Override
    public List<ValidationFailure> validate(ValidationContext<T, Map<K, V>> context) {
        List<ValidationFailure> failures = super.validate(context);

        Map<K, V> propertyValue = getPropertyFunc().apply(context.getInstanceToValidate());
        for (Map.Entry<K, V> entry : propertyValue.entrySet()) {

            if (predicate == null || predicate.test(entry)) {
                for (Constraint<?, Map.Entry<K, V>> entryConstraint : entryConstraints) {

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

//    @Override
//    public Function<Object, Map<K, V>> getPropertyFunc() {
//        return subject.getPropertyFunc();
//    }


}
