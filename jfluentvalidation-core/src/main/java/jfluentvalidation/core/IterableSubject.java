package jfluentvalidation.core;

import jfluentvalidation.constraints.iterable.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

// QUESTION: should we add generic T?
public class IterableSubject<T> extends Subject<IterableSubject<T>, Iterable<T>> {

    public IterableSubject(Function propertyFunc, String propertyName) {
        super(IterableSubject.class, propertyFunc, propertyName);
    }

    public final IterableSubject isEmpty() {
        constraints.add(new IsEmptyConstraint());
        return myself;
    }

    public final IterableSubject isNotEmpty() {
        constraints.add(new IsNotEmptyConstraint());
        return myself;
    }

    public final IterableSubject hasSize(int expectedSize) {
        constraints.add(new HasSizeConstraint(expectedSize));
        return myself;
    }

    public final IterableSubject contains(Object element) {
        constraints.add(new ContainsConstraint(element));
        return myself;
    }

    public final IterableSubject doesNotContain(Object element) {
        constraints.add(new DoesNotContainConstraint(element));
        return myself;
    }

    public final IterableSubject containsAnyOf(Object first, Object second, Object... rest) {
        // Google Truth has a decent accumulate method in SubjectUtils
        return myself;
    }

    public final IterableSubject containsAnyIn(Iterable<?> expected) {
        constraints.add(new ContainsAnyInConstraint(expected));
        return myself;
    }

    public final IterableSubject containsAnyIn(Object[] expected) {
        constraints.add(new ContainsAnyInConstraint(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject containsAllOf(Object first, Object second, Object... rest) {

        return myself;
    }

    public final IterableSubject containsAllIn(Iterable<?> expected) {
        constraints.add(new ContainsAnyInConstraint(expected));
        return myself;
    }

    public final IterableSubject containsAllIn(Object[] expected) {
        constraints.add(new ContainsAllInConstraint(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject containsExactly(Object... exactly) {
        // TODO: null check necessary?
        // TODO: remove guava newArrayList
        List<Object> expected = exactly == null
            ? newArrayList((Object) null)
            : asList(exactly);
        constraints.add(new ContainsExactlyElementsInConstraint(expected));
        return myself;
    }

    public final IterableSubject containsExactlyElementsIn(Iterable<?> expected) {
        constraints.add(new ContainsExactlyElementsInConstraint(expected));
        return myself;
    }

    public final IterableSubject containsExactlyElementsIn(Object[] expected) {
        constraints.add(new ContainsExactlyElementsInConstraint(Arrays.asList(expected)));
        return myself;
    }

    public final IterableSubject containsNoneOf(Object first, Object second, Object... rest) {

        return myself;
    }

    public final IterableSubject containsNoneIn(Iterable<?> excluded) {
        constraints.add(new ContainsNoneInConstraint(excluded));
        return myself;
    }

    public final IterableSubject containsNoneIn(Object[] excluded) {
        constraints.add(new ContainsNoneInConstraint(Arrays.asList(excluded)));
        return myself;
    }

    // isOrdered
}
