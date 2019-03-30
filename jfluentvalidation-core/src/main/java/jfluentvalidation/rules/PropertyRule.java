package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.Subject;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>
 * @param <P>
 */
public class PropertyRule<T, P> implements Rule<T, P> {

    private final Subject<?, P> subject;
    private final List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public PropertyRule(Subject<?, P> subject) {
        this.subject = subject;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        P propertyValue = subject.getPropertyFunc().apply(context.getInstanceToValidate());
        for (Constraint<? super P> constraint : subject.getConstraints()) {
            // boolean isValid = constraint.isValid(context.getPropertyValue());
            boolean isValid = constraint.isValid(propertyValue);
            if (!isValid) {
                String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                failures.add(new ValidationFailure(subject.getPropertyName(), errorMessage, propertyValue));
            }
        }

        return failures;
    }

    @Override
    public List<String> getRuleset() {
        return ruleSet;
    }

//    @Override
//    public Subject<?, P> getSubject() {
//        return subject;
//    }
}
