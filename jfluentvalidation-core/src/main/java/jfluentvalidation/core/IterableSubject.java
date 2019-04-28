package jfluentvalidation.core;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.iterable.*;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

/**
 *
 * @param <T>  the type of the instance
 */
public class IterableSubject<T, E> extends Subject<IterableSubject<T, E>, T, Iterable<? super E>> {

    public IterableSubject(PropertyRule<T, Iterable<? super E>> rule) {
        super(IterableSubject.class, rule);
    }

    public final IterableSubject<T, E> isEmpty() {
        rule.addConstraint(new IsEmptyConstraint<>());
        return myself;
    }

    public final IterableSubject<T, E> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint<>());
        return myself;
    }

    public final IterableSubject<T, E> hasSize(int expectedSize) {
        rule.addConstraint(new HasSizeConstraint<>(expectedSize));
        return myself;
    }

    public final IterableSubject<T, E> contains(E element) {
        rule.addConstraint(new ContainsConstraint<>(element));
        return myself;
    }

    public final IterableSubject<T, E> doesNotContain(E element) {
        rule.addConstraint(new DoesNotContainConstraint<>(element));
        return myself;
    }

    public final IterableSubject<T, E> containsAnyOf(E first, E second, E... rest) {
        // Google Truth has a decent accumulate method in SubjectUtils
        return myself;
    }

    public final IterableSubject<T, E> containsAnyIn(Iterable<E> expected) {
        rule.addConstraint(new ContainsAnyInConstraint<>(expected));
        return myself;
    }

    public final IterableSubject<T, E> containsAnyIn(E[] expected) {
        rule.addConstraint(new ContainsAnyInConstraint<>(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject<T, E> containsAllOf(E first, E second, E... rest) {

        return myself;
    }

    public final IterableSubject<T, E> containsAllIn(Iterable<E> expected) {
        rule.addConstraint(new ContainsAnyInConstraint<>(expected));
        return myself;
    }

    public final IterableSubject<T, E> containsAllIn(E[] expected) {
        rule.addConstraint(new ContainsAllInConstraint<>(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject<T, E> containsExactly(E... exactly) {
        // TODO: null check necessary?
        // TODO: remove guava newArrayList
        List<E> expected = exactly == null
            ? newArrayList((E) null)
            : asList(exactly);
        rule.addConstraint(new ContainsExactlyElementsInConstraint<>(expected));
        return myself;
    }

    public final IterableSubject<T, E> containsExactlyElementsIn(Iterable<? super E> expected) {
        rule.addConstraint(new ContainsExactlyElementsInConstraint<>(expected));
        return myself;
    }

    public final IterableSubject<T, E> containsExactlyElementsIn(E[] expected) {
        rule.addConstraint(new ContainsExactlyElementsInConstraint<>(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject<T, E> containsNoneOf(E first, E second, E... rest) {

        return myself;
    }

    public final IterableSubject<T, E> containsNoneIn(Iterable<? super E> excluded) {
        rule.addConstraint(new ContainsNoneInConstraint<>(excluded));
        return myself;
    }

    public final IterableSubject<T, E> containsNoneIn(E[] excluded) {
        rule.addConstraint(new ContainsNoneInConstraint<>(Arrays.asList(excluded)));
        return myself;
    }

    // isOrdered

    // TODO: the problem with this is that constraint isValid returns a boolean which doesn't work with this
    // is there a way to turn this into a rule? Problem being that we could define constraints for the actual iterable
    // as well as the items
    // Does IterableSubject need to include a collection of item constraints?
    // Should Subjects contain a rule instead of a list of constraints?
    // Does this need to be a varargs?
    /**
     *
     * @param constraintsToAdd
     * @return
     */
    public final IterableSubject<T, E> forEach(Constraint<T, E>... constraintsToAdd) {
        // TODO: fix unchecked warning
        rule.addConstraint(new ItemConstraint<>(constraintsToAdd));
        return myself;
    }

    /**
     *
     * @param predicate
     * @param constraintsToAdd
     * @return
     */
    public final IterableSubject<T, E> forEach(Predicate<E> predicate, Constraint<T, E>... constraintsToAdd) {
        rule.addConstraint(new ItemConstraint<>(predicate, constraintsToAdd));
        return myself;
    }

    // TODO: should we add a forEach that takes softConstraint?

    @Override
    public IterableSubject<T, E> isNull() {
        return super.isNull();
    }

    @Override
    public IterableSubject<T, E> isNotNull() {
        return super.isNotNull();
    }

    @Override
    public IterableSubject<T, E> isEquals(Iterable<? super E> other) {
        return super.isEquals(other);
    }
}
