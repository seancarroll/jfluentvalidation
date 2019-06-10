package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.*;
import java.util.function.Function;

public class CollectionPropertyRule<T, P> extends PropertyRule<T, P> {

    public CollectionPropertyRule(Function<T, P> propertyFunc, String propertyName) {
        super(propertyFunc, propertyName);
    }

    public CollectionPropertyRule(Class<T> type, Function<T, P> propertyFunc) {
        super(type, propertyFunc);
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        P propertyValue = propertyFunc.apply(context.getInstanceToValidate());
        Collection<Object> collectionPropertyValue = toCollection(propertyValue);
        if (collectionPropertyValue == null) {
            // TODO: throw
        }

        List<ValidationFailure> failures = new ArrayList<>();
        for (Constraint<?, ? extends P> constraint : getConstraints()) {
            int i = 0;
            for (Iterator<Object> it = collectionPropertyValue.iterator(); it.hasNext(); i++) {
                Object value = it.next();
                // TODO: is this the best way to handle this?
                RuleContext ruleContext = new RuleContext(context, this);
                boolean isValid = constraint.isValid(ruleContext);
                if (!isValid) {
                    ruleContext.appendArgument("index", i);
                    ruleContext.appendArgument("PropertyName", ruleContext.getRule().getPropertyName());
                    ruleContext.appendArgument("PropertyValue", ruleContext.getPropertyValue());

                    // TODO: instead of dynamically creating errorMessage use predfined error message similar to hibernate validator constraint annotations
                    //String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";

                    failures.add(new ValidationFailure(getPropertyName(), constraint.getOptions().getErrorMessage(), propertyValue));
//                    ConstraintViolation violation = new ConstraintViolation(getPropertyName(), errorMessage, value);
//                    violation.setFormattedMessagePlaceholderValues(ruleContext.getAdditionalArguments());
//                    failures.add(violation);
                }
            }

        }


        return failures;
    }

//    protected virtual void PrepareMessageFormatterForValidationError(PropertyValidatorContext context) {
//        context.MessageFormatter.AppendPropertyName(context.DisplayName);
//        context.MessageFormatter.AppendPropertyValue(context.PropertyValue);
//    }

    // https://stackoverflow.com/questions/2651632/how-to-check-if-an-object-is-a-collection-type-in-java
    private Collection<Object> toCollection(P propertyValue) {
        if (propertyValue instanceof List) {
            return (List) propertyValue;
        } else if (MoreArrays.isArray(propertyValue)) {
            return Arrays.asList((Object[])propertyValue);
        } else if (propertyValue instanceof Set) {
            return (Set) propertyValue;
        } else if (propertyValue instanceof Map) {
            return ((Map) propertyValue).entrySet();
        }

        throw new RuntimeException("collection property must validate a collection");
    }


}
