package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsCloseToConstraint;
import jfluentvalidation.constraints.time.IsNotCloseToConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

// ChronoUnit
// assertj has TemporalUnitOffset implements TemporalOffset<Temporal>  as well as TemporalUnitWithinOffset

/**
 * Base class for all implementations of assertions for {@link Temporal}s.
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public abstract class AbstractTemporalSubject<S extends AbstractTemporalSubject<S, T, A>, T, A extends Temporal & Comparable<? super A>>
    extends AbstractComparableSubject<S, T, A> {

    public AbstractTemporalSubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

    public S isCloseTo(A other, long offsetValue, TemporalUnit offsetUnit, boolean strict) {
        rule.addConstraint(new IsCloseToConstraint<>(other, offsetValue, offsetUnit, strict));
        return myself;
    }

    public S isNotCloseTo(A other, long offsetValue, TemporalUnit offsetUnit, boolean strict) {
        rule.addConstraint(new IsNotCloseToConstraint<>(other, offsetValue, offsetUnit, strict));
        return myself;
    }

}
