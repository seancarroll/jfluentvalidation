package jfluentvalidation.rules;

import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.*;

public class CollectionPropertyRule<T, P, E> extends PropertyRule<T, P> {

    private List<Constraint<T, ? super E>> itemConstraints = new ArrayList<>();

    public CollectionPropertyRule(SerializableFunction<T, P> propertyFunc, String propertyName) {
        super(propertyFunc, propertyName);
    }

    public CollectionPropertyRule(Class<T> type, SerializableFunction<T, P> propertyFunc) {
        super(type, propertyFunc);
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        P propertyValue = propertyFunc.apply(context.getInstanceToValidate());
        if (propertyValue == null) {
            // TODO: what do we want to do here?
            return failures;
        }

        Collection<E> collectionPropertyValue = (Collection<E>) toCollection(propertyValue);
        for (Constraint<?, ? extends P> constraint : getConstraints()) {
            // TODO: is this the best way to handle this?
            RuleContext ruleContext = new RuleContext(context, this);
            boolean isValid = constraint.isValid(ruleContext);
            if (!isValid) {
                failures.add(new ValidationFailure(getPropertyName(), constraint.getOptions().getErrorMessage(), propertyValue));
            }
        }

        // TODO: need to fix the following
        // - failures should include appropriate index in error message. Just put in propertyName?

        for (Constraint<T, ? super E> itemConstraint : itemConstraints) {
            int i = 0;
            for (E e : collectionPropertyValue) {
                ValidationContext childContext = new ValidationContext<>(e);
                PropertyRule<T, E> rule = new PropertyRule<>(null, propertyName);
                RuleContext ruleContext = new RuleContext(childContext, rule, e);
                boolean isValid = itemConstraint.isValid(ruleContext);
                if (!isValid) {
                    ruleContext.appendArgument("PropertyName", ruleContext.getRule().getPropertyName());
                    ruleContext.appendArgument("index", i);
                    ruleContext.appendArgument("PropertyValue", ruleContext.getPropertyValue());

                    failures.add(new ValidationFailure(getPropertyName(), itemConstraint.getOptions().getErrorMessage(), e));
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
    private Collection<?> toCollection(P propertyValue) {
        if (propertyValue instanceof List) {
            return (List) propertyValue;
        } else if (MoreArrays.isArray(propertyValue)) {
            return Arrays.asList((E[]) propertyValue);
        } else if (propertyValue instanceof Set) {
            return (Set) propertyValue;
        }

        throw new RuntimeException("collection property must validate a collection");
    }


    // TODO: should this just be addConstraints and take a varargs?
    public void addItemConstraint(Constraint<T, ? super E> constraint) {
        itemConstraints.add(constraint);
    }

}
