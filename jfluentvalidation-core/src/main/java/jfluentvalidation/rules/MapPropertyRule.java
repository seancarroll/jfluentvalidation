package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.Subject;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class MapPropertyRule<T, K, V> implements Rule<T, Map<K, V>> {

    private final Subject<?, Map<K, V>> subject;
    private final Predicate<Map.Entry<K, V>> predicate;
    private final List<String> ruleSet = RuleSet.DEFAULT_LIST;

    /**
     *
     */
    public MapPropertyRule(Subject<?, Map<K, V>> subject) {
        this(subject, null);
    }

    /**
     * @param predicate
     */
    public MapPropertyRule(Subject<?, Map<K, V>> subject, Predicate<Map.Entry<K, V>> predicate) {
        this.subject = subject;
        this.predicate = predicate;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, Map<K, V>> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        Map<K, V> propertyValue = subject.getPropertyFunc().apply(context.getInstanceToValidate());
        for (Map.Entry<K, V> entry : propertyValue.entrySet()) {

            if (predicate == null || predicate.test(entry)) {
                for (Constraint<? super Map<K, V>> constraint : subject.getConstraints()) {

                }
            }

        }

        return failures;
    }

    @Override
    public List<String> getRuleset() {
        return ruleSet;
    }

//    @Override
//    public Subject<?, Map<K, V>> getSubject() {
//        return subject;
//    }
}
