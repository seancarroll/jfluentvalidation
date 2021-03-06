package jfluentvalidation.rules;

import jfluentvalidation.MapItemConstraint;
import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.messageinterpolation.ResourceBundleMessageInterpolator;
import jfluentvalidation.validators.ConstraintContext;
import jfluentvalidation.validators.RuleOptions;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class MapPropertyRule<T, K, V> extends PropertyRule<T, Map<K, V>> {

    private List<MapItemConstraint<T, K, V, ?>> itemConstraints = new ArrayList<>();
    private ResourceBundleMessageInterpolator interpolator = new ResourceBundleMessageInterpolator();

    public MapPropertyRule(SerializableFunction<T, Map<K, V>> propertyFunc, String propertyName, RuleOptions ruleOptions) {
        super(propertyFunc, propertyName, ruleOptions);
    }

    public MapPropertyRule(Class<T> type, SerializableFunction<T, Map<K, V>> propertyFunc, RuleOptions ruleOptions) {
        super(type, propertyFunc, ruleOptions);
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        Map<K, V> propertyValue = propertyFunc.apply(context.getInstanceToValidate());

        for (Constraint<T, Map<K, V>> constraint : getConstraints()) {
            // TODO: is this the best way to handle this?
            ConstraintContext<T, Map<K, V>> constraintContext = new ConstraintContext<>(context, this);
            boolean isValid = constraint.isValid(constraintContext);
            if (!isValid) {
                constraintContext.getMessageContext().appendPropertyName(constraintContext.getRule().getPropertyName());
                constraintContext.getMessageContext().appendPropertyValue(constraintContext.getPropertyValue());
                constraint.addParametersToContext(constraintContext);
                String resolvedMessage = interpolator.interpolate(constraint.getOptions().getErrorMessage(), constraintContext.getMessageContext().getPlaceholderValues());
                failures.add(new ValidationFailure(getPropertyName(), resolvedMessage, propertyValue));
            }
        }

        // TODO: need to fix the following
        // - failures should include appropriate index in error message. Just put in propertyName?
        // TODO: not a fan of this...
        if (propertyValue != null) {
            for (MapItemConstraint<T, K, V, ?> itemConstraint : itemConstraints) {
                int i = 0;
                for (Object e : itemConstraint.getCollection(propertyValue)) {
                    // TODO: this is yucky. need to fix/clean up/improve
                    ValidationContext<?> childContext = new ValidationContext<>(e);
                    PropertyRule<T, Object> rule = new PropertyRule<>(null, propertyName, ruleOptions);
                    ConstraintContext constraintContext = new ConstraintContext(childContext, rule, e);
                    boolean isValid = itemConstraint.getConstraint().isValid(constraintContext);
                    if (!isValid) {
                        // TODO: how to include index in message
                        constraintContext.getMessageContext().appendPropertyName(constraintContext.getRule().getPropertyName());
                        constraintContext.getMessageContext().appendArgument("index", i);
                        constraintContext.getMessageContext().appendPropertyValue(constraintContext.getPropertyValue());
                        itemConstraint.getConstraint().addParametersToContext(constraintContext);

                        String resolvedMessage = interpolator.interpolate(itemConstraint.getConstraint().getOptions().getErrorMessage(), constraintContext.getMessageContext().getPlaceholderValues());

                        failures.add(new ValidationFailure(getPropertyName(), resolvedMessage, e));
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
