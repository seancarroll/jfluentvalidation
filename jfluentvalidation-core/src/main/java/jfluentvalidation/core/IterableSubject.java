package jfluentvalidation.core;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.iterable.*;
import jfluentvalidation.constraints.CollectionConstraint;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

// QUESTION: should we add generic T?
public class IterableSubject<T> extends Subject<IterableSubject<T>, Iterable<T>> {

    public IterableSubject(Function propertyFunc, String propertyName) {
        super(IterableSubject.class, propertyFunc, propertyName);
    }

    public final IterableSubject<T> isEmpty() {
        constraints.add(new IsEmptyConstraint());
        return myself;
    }

    public final IterableSubject<T> isNotEmpty() {
        constraints.add(new IsNotEmptyConstraint());
        return myself;
    }

    public final IterableSubject<T> hasSize(int expectedSize) {
        constraints.add(new HasSizeConstraint(expectedSize));
        return myself;
    }

    public final IterableSubject<T> contains(Object element) {
        constraints.add(new ContainsConstraint(element));
        return myself;
    }

    public final IterableSubject<T> doesNotContain(Object element) {
        constraints.add(new DoesNotContainConstraint(element));
        return myself;
    }

    public final IterableSubject<T> containsAnyOf(Object first, Object second, Object... rest) {
        // Google Truth has a decent accumulate method in SubjectUtils
        return myself;
    }

    public final IterableSubject<T> containsAnyIn(Iterable<?> expected) {
        constraints.add(new ContainsAnyInConstraint(expected));
        return myself;
    }

    public final IterableSubject<T> containsAnyIn(Object[] expected) {
        constraints.add(new ContainsAnyInConstraint(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject<T> containsAllOf(Object first, Object second, Object... rest) {

        return myself;
    }

    public final IterableSubject<T> containsAllIn(Iterable<?> expected) {
        constraints.add(new ContainsAnyInConstraint(expected));
        return myself;
    }

    public final IterableSubject<T> containsAllIn(Object[] expected) {
        constraints.add(new ContainsAllInConstraint(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject<T> containsExactly(Object... exactly) {
        // TODO: null check necessary?
        // TODO: remove guava newArrayList
        List<Object> expected = exactly == null
            ? newArrayList((Object) null)
            : asList(exactly);
        constraints.add(new ContainsExactlyElementsInConstraint(expected));
        return myself;
    }

    public final IterableSubject<T> containsExactlyElementsIn(Iterable<?> expected) {
        constraints.add(new ContainsExactlyElementsInConstraint(expected));
        return myself;
    }

    public final IterableSubject<T> containsExactlyElementsIn(Object[] expected) {
        constraints.add(new ContainsExactlyElementsInConstraint(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject<T> containsNoneOf(Object first, Object second, Object... rest) {

        return myself;
    }

    public final IterableSubject<T> containsNoneIn(Iterable<?> excluded) {
        constraints.add(new ContainsNoneInConstraint(excluded));
        return myself;
    }

    public final IterableSubject<T> containsNoneIn(Object[] excluded) {
        constraints.add(new ContainsNoneInConstraint(Arrays.asList(excluded)));
        return myself;
    }

    // isOrdered


    public final IterableSubject<T> forEach(Predicate<? super T> predicate, Constraint<? super T>... constraintsToAdd) {
        // TODO: what should we do here? What about a something like CollectionConstraint? CollectionItemConstraint?
        // fluentValidation has PropertyRule and CollectionPropertyRule so maybe CollectionConstraint doesn't suck too much
        constraints.add(new CollectionConstraint<>(predicate, constraintsToAdd));
        return myself;
    }

    public final IterableSubject<T> forEach(Constraint<? super T>... constraintsToAdd) {
        // TODO: what should we do here? What about a something like CollectionConstraint? CollectionItemConstraint?
        // fluentValidation has PropertyRule and CollectionPropertyRule so maybe CollectionConstraint doesn't suck too much
        constraints.add(new CollectionConstraint<>(constraintsToAdd));
        return myself;
    }

    @Override
    public IterableSubject<T> isNull() {
        return super.isNull();
    }

    @Override
    public IterableSubject<T> isNotNull() {
        return super.isNotNull();
    }

    @Override
    public IterableSubject<T> isEquals(Iterable<T> other) {
        return super.isEquals(other);
    }
}
