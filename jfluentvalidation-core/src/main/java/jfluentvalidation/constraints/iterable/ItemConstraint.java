package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Collection;
import java.util.function.Predicate;

// QUESTION: how is this different than SoftConstraint? Should this use it?
// This looks like a more general case of the ContainsAllIn/ContainsAnyIn/ContainsExactlyElements/ContainsNoneIn/etc
public class ItemConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final Predicate<P> condition;
    // TODO: should this just be a single constraint?
    private final Constraint<T, P>[] innerConstraints;

    public ItemConstraint(Predicate<P> condition, Constraint<T, P>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        // TODO: fix...need to collect results and return
        // TODO: does it make sense to move this out somewhere common?
        Collection<P> actual = (Collection<P>) Iterables.toCollection(context.getPropertyValue());
        for (P item : actual) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(item)) {
                for (Constraint c : innerConstraints) {
                    // TODO: better way to do this? not sure this is even correct when using the RuleContext
                    ValidationContext childContext = new ValidationContext(item);
                    c.isValid(new RuleContext(childContext, context.getRule()));

                }
            }
        }

        return false;
    }
}
