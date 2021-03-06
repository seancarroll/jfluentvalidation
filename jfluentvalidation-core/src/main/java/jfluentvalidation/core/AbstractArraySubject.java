package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.SoftItemConstraint;
import jfluentvalidation.rules.CollectionPropertyRule;
import jfluentvalidation.rules.PropertyRule;

import java.util.function.Predicate;

/**
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}. TODO: do we want to use A here given type is actually an array? Do we want to use E?
 * @param <E>  the type of the "actual" array element.
 */
public abstract class AbstractArraySubject<S extends AbstractArraySubject<S, T, A, E>, T, A, E>
    extends Subject<S, T, A>
    implements ArraySubject<S, A, E> {

    public AbstractArraySubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

    /**
     *
     * @param constraintsToAdd
     * @return
     */
    @CanIgnoreReturnValue
    public final S forEach(Constraint<T, ? super E>... constraintsToAdd) {
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
    @CanIgnoreReturnValue
    public final S forEach(Predicate<? super E> predicate, Constraint<T, E>... constraintsToAdd) {
        for (Constraint<T, E> constraintToAdd : constraintsToAdd) {
            getRule().addItemConstraint(new SoftItemConstraint<>(predicate, constraintToAdd));
        }
        return myself;
    }

    @Override
    protected CollectionPropertyRule<T, A, E> getRule() {
        return (CollectionPropertyRule<T, A, E>) super.getRule();
    }
}
