package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

import java.util.Collection;
import java.util.function.Predicate;

// QUESTION: how is this different than SoftConstraint? Should this use it?
// This looks like a more general case of the ContainsAllIn/ContainsAnyIn/ContainsExactlyElements/ContainsNoneIn/etc
public class ItemConstraint<T> implements Constraint<Iterable<? super T>> {

    private final Predicate<T> condition;
    // TODO: should this just be a single constraint?
    private final Constraint<T>[] innerConstraints;

    public ItemConstraint(Predicate<T> condition, Constraint<T>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(Iterable<? super T> value) {
        // TODO: fix...need to collect results and return
        // TODO: does it make sense to move this out somewhere common?
        Collection<T> actual = (Collection<T>) Iterables.toCollection(value);
        for (T item : actual) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(item)) {
                for (Constraint c : innerConstraints) {
                    c.isValid(item);
                }
            }
        }

        return false;
    }
}
