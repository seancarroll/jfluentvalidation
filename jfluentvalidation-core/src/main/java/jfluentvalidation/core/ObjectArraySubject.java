package jfluentvalidation.core;

import jfluentvalidation.constraints.array.contains.ContainsObjectConstraint;
import jfluentvalidation.constraints.array.containsallof.ContainsAllOfObjectConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyObjectConstraint;
import jfluentvalidation.constraints.array.containsnone.ContainsNoneObjectConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyObjectArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyObjectArrayConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyObjectArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyObjectArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * A Subject for {@code Object[]} and more generically {@code T[]}.
 *
 * @param <T>  the type of the instance
 * @param <E>  the type of elements in the actual array subject.
 */
public class ObjectArraySubject<T, E> extends AbstractArraySubject<ObjectArraySubject<T, E>, T, E[], E> {

    public ObjectArraySubject(PropertyRule<T, E[]> rule) {
        super(ObjectArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyObjectArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyObjectArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyObjectArrayConstraint<>());
    }

    @Override
    public ObjectArraySubject<T, E> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyObjectArrayConstraint<>());
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> hasLength(int expected) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> hasMinimumLength(int min) {
        rule.addConstraint(new ObjectArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> hasMaximumLength(int max) {
        rule.addConstraint(new ObjectArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> hasLengthBetween(int min, int max) {
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public ObjectArraySubject<T, E> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new ObjectArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }


    //    /**
//     * Assert that the actual array has the same size as the other array.
//     *
//     * @param info contains information about the assertion.
//     * @param actual the given array.
//     * @param other the group to compare
//     * @throws AssertionError if the actual group is {@code null}.
//     * @throws AssertionError if the other group is {@code null}.
//     * @throws AssertionError if the actual group does not have the same size.
//     */
    @Override
    public ObjectArraySubject<T, E> hasSameLengthAs(Iterable<E> other) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> hasSameLengthAs(E[] other) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> contains(E value) {
        rule.addConstraint(new ContainsObjectConstraint<>(value));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> containsAnyOf(E... values) {
        return containsAnyOf(Arrays.asList(values));
    }

    @Override
    public ObjectArraySubject<T, E> containsAnyOf(Iterable<E> values) {
        rule.addConstraint(new ContainsAnyObjectConstraint<>(values));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> containsAllOf(E... expected) {
        return containsAllOf(Arrays.asList(expected));
    }

    @Override
    public ObjectArraySubject<T, E> containsAllOf(Iterable<E> expected) {
        rule.addConstraint(new ContainsAllOfObjectConstraint<>(expected));
        return myself;
    }

    @Override
    public ObjectArraySubject<T, E> containsExactly(E... exactly) {
        return null;
    }

    @Override
    public ObjectArraySubject<T, E> containsExactly(Iterable<E> expected) {
        return null;
    }

    @Override
    public ObjectArraySubject<T, E> containsNone(E... values) {
        return containsNone(Arrays.asList(values));
    }

    @Override
    public ObjectArraySubject<T, E> containsNone(Iterable<E> values) {
        rule.addConstraint(new ContainsNoneObjectConstraint<>(values));
        return myself;
    }


//
//    // TODO: the problem with this is that constraint isValid returns a boolean which doesn't work with this
//    // is there a way to turn this into a rule? Problem being that we could define constraints for the actual iterable
//    // as well as the items
//    // Does IterableSubject need to include a collection of item constraints?
//    // Should Subjects contain a rule instead of a list of constraints?
//    // Does this need to be a varargs?
//    /**
//     *
//     * @param constraintsToAdd
//     * @return
//     */
//    public ObjectArraySubject<T> forEach(Constraint<T, Object>... constraintsToAdd) {
//        // QUESTION: what if I didnt use the existing rule but created a new CollectionPropertyRule?
//        // would need to have access to the Subject to do so which might not be a good idea
//        // Otherwise we need to change Constraint to violations
//        rule.addConstraint(new ItemConstraint<>(MoreArrays.array(constraintsToAdd)));
//        return myself;
//    }
//
//    /**
//     *
//     * @param predicate
//     * @param constraintsToAdd
//     * @return
//     */
//    public ObjectArraySubject<T> forEach(Predicate<Object> predicate, Constraint<T, Object>... constraintsToAdd) {
//        rule.addConstraint(new ItemConstraint<>(predicate, constraintsToAdd));
//        return myself;
//    }


}
