package jfluentvalidation.rules;

import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.messageinterpolation.ResourceBundleMessageInterpolator;
import jfluentvalidation.validators.ConstraintContext;
import jfluentvalidation.validators.RuleOptions;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @param <T>
 * @param <P>
 * @param <E>
 */
public class CollectionPropertyRule<T, P, E> extends PropertyRule<T, P> {

    private List<Constraint<T, ? super E>> itemConstraints = new ArrayList<>();
    private ResourceBundleMessageInterpolator interpolator = new ResourceBundleMessageInterpolator();

    public CollectionPropertyRule(SerializableFunction<T, P> propertyFunc, String propertyName, RuleOptions ruleOptions) {
        super(propertyFunc, propertyName, ruleOptions);
    }

    public CollectionPropertyRule(Class<T> type, SerializableFunction<T, P> propertyFunc, RuleOptions ruleOptions) {
        super(type, propertyFunc, ruleOptions);
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        P propertyValue = propertyFunc.apply(context.getInstanceToValidate());

        Collection<E> collectionPropertyValue = toCollection(propertyValue);
        for (Constraint<?, ? extends P> constraint : getConstraints()) {
            // TODO: is this the best way to handle this?
            ConstraintContext ruleContext = new ConstraintContext(context, this);
            boolean isValid = constraint.isValid(ruleContext);
            if (!isValid) {
                ruleContext.getMessageContext().appendPropertyName(ruleContext.getRule().getPropertyName());
                ruleContext.getMessageContext().appendPropertyValue(ruleContext.getPropertyValue());
                constraint.addParametersToContext(ruleContext);
                // TODO: locale?
                String resolvedMessage = interpolator.interpolate(constraint.getOptions().getErrorMessage(), ruleContext.getMessageContext().getPlaceholderValues());
                failures.add(new ValidationFailure(getPropertyName(), resolvedMessage, propertyValue));
            }
        }

        // TODO: need to fix the following
        // - failures should include appropriate index in error message. Just put in propertyName?
        if (propertyValue != null) {
            for (Constraint<T, ? super E> itemConstraint : itemConstraints) {
                int i = 0;
                for (E e : collectionPropertyValue) {
                    ValidationContext childContext = new ValidationContext<>(e);
                    PropertyRule<T, E> rule = new PropertyRule<>(null, propertyName, ruleOptions);
                    ConstraintContext ruleContext = new ConstraintContext(childContext, rule, e);
                    boolean isValid = itemConstraint.isValid(ruleContext);
                    if (!isValid) {
                        ruleContext.getMessageContext().appendPropertyName(ruleContext.getRule().getPropertyName());
                        ruleContext.getMessageContext().appendArgument("index", i);
                        ruleContext.getMessageContext().appendPropertyValue(ruleContext.getPropertyValue());
                        itemConstraint.addParametersToContext(ruleContext);
                        // TODO: locale?
                        String resolvedMessage = interpolator.interpolate(itemConstraint.getOptions().getErrorMessage(), ruleContext.getMessageContext().getPlaceholderValues());

                        failures.add(new ValidationFailure(getPropertyName(), resolvedMessage, e));
                    }
                }
//            for (Iterator<E> it = itemConstraint.getCollection(collectionPropertyValue.iterator()); it.hasNext(); i++) {
//                // TODO: ugh...this is trash
//                E item = it.next();
//                ValidationContext childContext = new ValidationContext<>(item);
//                PropertyRule<T, E> rule = new PropertyRule<>(null, propertyName);
//                RuleContext ruleContext = new RuleContext(childContext, rule, item);
//                boolean isValid = itemConstraint.isValid(ruleContext);
//                if (!isValid) {
//                    ruleContext.appendArgument("PropertyName", ruleContext.getRule().getPropertyName());
//                    ruleContext.appendArgument("index", i);
//                    ruleContext.appendArgument("PropertyValue", ruleContext.getPropertyValue());
//
//                    failures.add(new ValidationFailure(getPropertyName(), itemConstraint.getOptions().getErrorMessage(), item));
//                }
//            }
            }
        }


//        for (Constraint<?, ? extends P> constraint : getConstraints()) {
//            int i = 0;
//            for (Iterator<Object> it = collectionPropertyValue.iterator(); it.hasNext(); i++) {
//                Object value = it.next();
//                // TODO: is this the best way to handle this?
//                RuleContext ruleContext = new RuleContext(context, this);
//                boolean isValid = constraint.isValid(ruleContext);
//                if (!isValid) {
//                    ruleContext.appendArgument("index", i);
//                    ruleContext.appendArgument("PropertyName", ruleContext.getRule().getPropertyName());
//                    ruleContext.appendArgument("PropertyValue", ruleContext.getPropertyValue());
//
//                    // TODO: instead of dynamically creating errorMessage use predfined error message similar to hibernate validator constraint annotations
//                    //String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
//
//                    failures.add(new ValidationFailure(getPropertyName(), constraint.getOptions().getErrorMessage(), propertyValue));
////                    ConstraintViolation violation = new ConstraintViolation(getPropertyName(), errorMessage, value);
////                    violation.setFormattedMessagePlaceholderValues(ruleContext.getAdditionalArguments());
////                    failures.add(violation);
//                }
//            }
//
//        }

        return failures;
    }

//    protected virtual void PrepareMessageFormatterForValidationError(PropertyValidatorContext context) {
//        context.MessageFormatter.AppendPropertyName(context.DisplayName);
//        context.MessageFormatter.AppendPropertyValue(context.PropertyValue);
//    }

    // https://stackoverflow.com/questions/2651632/how-to-check-if-an-object-is-a-collection-type-in-java
    private Collection<E> toCollection(P propertyValue) {
        if (propertyValue == null) {
            return null;
        }

        if (propertyValue instanceof List) {
            return (List<E>) propertyValue;
        } else if (MoreArrays.isArray(propertyValue)) {
            return Arrays.asList((E[]) propertyValue);
        } else if (propertyValue instanceof Set) {
            return (Set<E>) propertyValue;
        }

        throw new RuntimeException("collection property must validate a collection");
    }


    // TODO: should this just be addConstraints and take a varargs?
    public void addItemConstraint(Constraint<T, ? super E> constraint) {
        itemConstraints.add(constraint);
    }

}
