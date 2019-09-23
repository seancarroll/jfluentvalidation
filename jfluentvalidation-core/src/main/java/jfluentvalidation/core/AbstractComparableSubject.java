package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.IsBetweenConstraint;
import jfluentvalidation.constraints.comparable.IsEqualAccordingToCompareToConstraint;
import jfluentvalidation.constraints.comparable.IsGreaterThanConstraint;
import jfluentvalidation.constraints.comparable.IsGreaterThanOrEqualToConstraint;
import jfluentvalidation.constraints.comparable.IsLessThanConstraint;
import jfluentvalidation.constraints.comparable.IsLessThanOrEqualToConstraint;
import jfluentvalidation.constraints.comparable.IsNotBetweenConstraint;
import jfluentvalidation.constraints.comparable.IsNotEqualAccordingToCompareToConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public class AbstractComparableSubject<S extends AbstractComparableSubject<S, T, A>, T, A extends Comparable<? super A>>
    extends Subject<S, T, A>
    implements ComparableSubject<S, T, A> {

    public AbstractComparableSubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

    @Override
    public S isEqualAccordingToCompareTo(A other) {
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(other));
        return myself;
    }

    @Override
    public S isNotEqualAccordingToCompareTo(A other) {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(other));
        return myself;
    }

    @Override
    public S isLessThan(A other) {
        rule.addConstraint(new IsLessThanConstraint<>(other));
        return myself;
    }

    @Override
    public S isLessThanOrEqualTo(A other) {
        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(other));
        return myself;
    }

    @Override
    public S isGreaterThan(A other) {
        rule.addConstraint(new IsGreaterThanConstraint<>(other));
        return myself;
    }

    @Override
    public S isGreaterThanOrEqualTo(A other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(other));
        return myself;
    }

    @Override
    public S isBetween(A startInclusive, A endInclusive) {
        return isBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public S isStrictlyBetween(A startExclusive, A endExclusive) {
        return isBetween(startExclusive, endExclusive, false, false);
    }

    @Override
    public S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public S isNotBetween(A startInclusive, A endInclusive) {
        return isNotBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public S isNotBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsNotBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

}
