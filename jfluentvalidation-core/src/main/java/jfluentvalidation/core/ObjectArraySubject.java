package jfluentvalidation.core;

import jfluentvalidation.constraints.array.empty.IsEmptyObjectArrayConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyObjectArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class ObjectArraySubject<T> extends AbstractArraySubject<ObjectArraySubject<T>, T, Object[], Object> {

    public ObjectArraySubject(PropertyRule<T, Object[]> rule) {
        super(ObjectArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyObjectArrayConstraint<>());
    }

    @Override
    public ObjectArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyObjectArrayConstraint<>());
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new ObjectArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new ObjectArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new ObjectArrayBetweenLengthConstraint<>(min, max));
        return myself;
    }

//    @Override
//    public ObjectArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
//        rule.addConstraint(new ObjectArrayBetweenLengthConstraint<>());
//        return myself;
//    }

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
    public ObjectArraySubject<T> hasSameLengthAs(Iterable<?> other) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSameLengthAs(Object other) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(other));
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
