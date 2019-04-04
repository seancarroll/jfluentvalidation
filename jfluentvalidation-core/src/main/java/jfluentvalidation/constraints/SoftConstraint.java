package jfluentvalidation.constraints;

import java.util.function.Predicate;

// QUESTION: Is this a good name?
// fluentvalidation calls this a DelegatingValidator
// QUESTION: is this even a good abstraction?

// TODO: I think I f'ed this up
// The common scenario would be for something like
// RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
// In this example the the x is of type Person not of type String (Country)
// Another example
// RuleFor(x => x.Discount).NotEqual(0).When(x => x.HasDiscount);

/**
 *
 * @param <T> the target type supported by an implementation
 */
public class SoftConstraint<T> implements Constraint<T> {

    private final Predicate<? super T> condition;
    private final Constraint<? super T> innerConstraint;

    public SoftConstraint(Predicate<? super T> condition, Constraint<? super T> innerConstraint) {
        this.condition = condition;
        this.innerConstraint = innerConstraint;
    }

    @Override
    public boolean isValid(T instance) {
        if (condition.test(instance)) {
            return innerConstraint.isValid(instance);
        }

        return true;
    }
}
