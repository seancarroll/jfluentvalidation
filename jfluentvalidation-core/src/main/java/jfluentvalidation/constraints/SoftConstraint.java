package jfluentvalidation.constraints;

import jfluentvalidation.validators.RuleContext;

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
 * @param <T>  the target type supported by an implementation.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class SoftConstraint<T, P> extends AbstractConstraint<T, P> {

    private final Predicate<T> condition;
    private final Constraint<T, P> innerConstraint;

    public SoftConstraint(Predicate<T> condition, Constraint<T, P> innerConstraint) {
        super(innerConstraint.getOptions().getErrorMessage());
        this.condition = condition;
        this.innerConstraint = innerConstraint;
    }

    @Override
    public boolean isValid(RuleContext<T, P> validationContext) {
        if (condition.test(validationContext.getInstanceToValidate())) {
            return innerConstraint.isValid(validationContext);
        }

        return true;
    }

//    @Override
//    public String getMessage() {
//        return innerConstraint.getMessage();
//    }
}
