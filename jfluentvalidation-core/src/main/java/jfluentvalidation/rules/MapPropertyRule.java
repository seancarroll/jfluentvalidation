package jfluentvalidation.rules;

import jfluentvalidation.MapItemConstraint;
import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.*;

public class MapPropertyRule<T, K, V> extends PropertyRule<T, Map<K, V>> {

    private List<MapItemConstraint<T, K, V, ?>> itemConstraints = new ArrayList<>();

    public MapPropertyRule(SerializableFunction<T, Map<K, V>> propertyFunc, String propertyName) {
        super(propertyFunc, propertyName);
    }

    public MapPropertyRule(Class<T> type, SerializableFunction<T, Map<K, V>> propertyFunc) {
        super(type, propertyFunc);
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, Map<K, V>> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        Map<K, V> propertyValue = propertyFunc.apply(context.getInstanceToValidate());

        for (Constraint<T, Map<K, V>> constraint : getConstraints()) {
            // TODO: is this the best way to handle this?
            RuleContext<T, Map<K, V>> ruleContext = new RuleContext<>(context, this);
            boolean isValid = constraint.isValid(ruleContext);
            if (!isValid) {
                failures.add(new ValidationFailure(getPropertyName(), constraint.getOptions().getErrorMessage(), propertyValue));
            }
        }

        // TODO: need to fix the following
        // - failures should include appropriate index in error message. Just put in propertyName?

        if (propertyValue != null) {
            for (MapItemConstraint<T, K, V, ?> itemConstraint : itemConstraints) {
                int i = 0;
                for (Object e : itemConstraint.getCollection(propertyValue)) {
                    // TODO: this is yucky. need to fix/clean up/improve
                    ValidationContext childContext = new ValidationContext<>(e);
                    PropertyRule<T, Object> rule = new PropertyRule<>(null, propertyName);
                    RuleContext ruleContext = new RuleContext(childContext, rule, e);
                    boolean isValid = itemConstraint.getConstraint().isValid(ruleContext);
                    if (!isValid) {
                        ruleContext.appendArgument("PropertyName", ruleContext.getRule().getPropertyName());
                        ruleContext.appendArgument("index", i);
                        ruleContext.appendArgument("PropertyValue", ruleContext.getPropertyValue());

                        failures.add(new ValidationFailure(getPropertyName(), itemConstraint.getConstraint().getOptions().getErrorMessage(), e));
                    }
                }
            }
        }

        return failures;
    }


    // TODO: should this just be addConstraints and take a varargs?
    public void addItemConstraint(MapItemConstraint<T, K, V, ?> constraint) {
        itemConstraints.add(constraint);
    }

}
