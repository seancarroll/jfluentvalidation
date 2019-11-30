package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.SoftItemConstraint;
import jfluentvalidation.constraints.iterable.ContainsAllConstraint;
import jfluentvalidation.constraints.iterable.ContainsAnyConstraint;
import jfluentvalidation.constraints.iterable.ContainsConstraint;
import jfluentvalidation.constraints.iterable.ContainsExactlyConstraint;
import jfluentvalidation.constraints.iterable.ContainsNoneConstraint;
import jfluentvalidation.constraints.iterable.DoesNotContainConstraint;
import jfluentvalidation.constraints.iterable.HasSizeConstraint;
import jfluentvalidation.constraints.iterable.IsEmptyConstraint;
import jfluentvalidation.constraints.iterable.IsNotEmptyConstraint;
import jfluentvalidation.rules.CollectionPropertyRule;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static jfluentvalidation.common.Lists.newArrayList;

/**
 *
 * @param <T>  the type of the instance
 * @param <E>  the type of the "actual" array element.
 */
public class IterableSubject<T, E> extends AbstractIterableSubject<IterableSubject<T, E>, T, Iterable<? super E>, E> {

    public IterableSubject(CollectionPropertyRule<T, Iterable<? super E>, E> rule) {
        super(IterableSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> isEmpty() {
        rule.addConstraint(new IsEmptyConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> hasSize(int expectedSize) {
        rule.addConstraint(new HasSizeConstraint<>(expectedSize));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> hasSameSizeAs(Iterable<E> other) {
        rule.addConstraint(new HasSizeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> contains(E element) {
        rule.addConstraint(new ContainsConstraint<>(element));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> doesNotContain(E element) {
        rule.addConstraint(new DoesNotContainConstraint<>(element));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsAnyOf(E... values) {
        rule.addConstraint(new ContainsAnyConstraint<>(Arrays.asList(values)));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsAnyOf(Iterable<E> values) {
        rule.addConstraint(new ContainsAnyConstraint<>(values));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsAllOf(E... expected) {
        rule.addConstraint(new ContainsAllConstraint<>(Arrays.asList(expected)));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsAllOf(Iterable<E> expected) {
        rule.addConstraint(new ContainsAnyConstraint<>(expected));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsExactly(E... exactly) {
        // TODO: null check necessary?
        // TODO: remove guava newArrayList
        List<E> expected = exactly == null
            ? newArrayList((E) null)
            : asList(exactly);
        rule.addConstraint(new ContainsExactlyConstraint<>(expected));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsExactly(Iterable<E> expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(expected));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsNone(E... values) {
        rule.addConstraint(new ContainsNoneConstraint<>(Arrays.asList(values)));
        return myself;
    }

    @CanIgnoreReturnValue
    public final IterableSubject<T, E> containsNone(Iterable<? super E> values) {
        rule.addConstraint(new ContainsNoneConstraint<>(values));
        return myself;
    }

//    // TODO: the problem with this is that constraint isValid returns a boolean which doesn't work with this
//    // is there a way to turn this into a rule? Problem being that we could define constraints for the actual iterable
//    // as well as the items
//    // Does IterableSubject need to include a collection of item constraints?
//    // Should Subjects contain a rule instead of a list of constraints?
//    // Does this need to be a varargs?
    /**
     *
     * @param constraintsToAdd
     * @return
     */
    public final IterableSubject<T, E> forEach(Constraint<T, E>... constraintsToAdd) {
        for (Constraint<T, ? super E> constraintToAdd : constraintsToAdd) {
            getRule().addItemConstraint(constraintToAdd);
        }
        return myself;
    }

    /**
     *
     * @param predicate
     * @param constraintsToAdd
     * @return
     */
    public final IterableSubject<T, E> forEach(Predicate<? super E> predicate, Constraint<T, E>... constraintsToAdd) {
        for (Constraint<T, E> constraintToAdd : constraintsToAdd) {
            getRule().addItemConstraint(new SoftItemConstraint<>(predicate, constraintToAdd));
        }
        return myself;
    }

    @Override
    protected CollectionPropertyRule<T, Iterable<? super E>, E> getRule() {
        return (CollectionPropertyRule<T, Iterable<? super E>, E>) super.getRule();
    }
}
