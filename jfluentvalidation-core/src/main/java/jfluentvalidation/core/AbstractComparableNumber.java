package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;
import jfluentvalidation.rules.PropertyRule;

// TODO: looks like we cant use anything that uses a constant because of issues with generic types.
// AssertJ look to get around this by having individual assert classes for all the number types which
// kinda sucks

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public abstract class AbstractComparableNumber<S extends AbstractComparableNumber<S, T, A>, T, A extends Number & Comparable<A>>
    extends Subject<S, T, A>
    implements NumberSubject<S, A>, ComparableSubject<S, T, A> {

    public AbstractComparableNumber(Class<?> selfType, PropertyRule<T, A> rule) {
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
        rule.addConstraint(new IsBetweenConstraint<>(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public S isStrictlyBetween(A startExclusive, A endExclusive) {
        rule.addConstraint(new IsBetweenConstraint<>(startExclusive, endExclusive, true, true));
        return myself;
    }

    @Override
    public S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    public S isNotBetwen(A start, A end) {
        // TODO: should it be inclusive start and end?
        rule.addConstraint(new IsNotBetweenConstraint<>(start, end, true, true));
        return myself;
    }

    public S isNotBetwen(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: should it be inclusive start and end?
        rule.addConstraint(new IsNotBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public S isZero() {
        // TODO: what should the localization key be for this?
        // Do we want to tie it to constraint name?
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(zero()));
        return myself;
    }

    @Override
    public S isNotZero() {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(zero()));
        return myself;
    }

    @Override
    public S isOne() {
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(one()));
        return myself;
    }

    @Override
    public S isNotOne() {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(one()));
        return myself;
    }

    @Override
    public S isPositive() {
        rule.addConstraint(new IsGreaterThanConstraint<>(zero()));
        return myself;
    }

    @Override
    public S isNotPositive() {
        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(zero()));
        return myself;
    }

    @Override
    public S isNegative() {
        rule.addConstraint(new IsLessThanConstraint<>(zero()));
        return myself;
    }

    @Override
    public S isNotNegative() {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(zero()));
        return myself;
    }

    @Override
    public S isCloseTo() {
        return null;
    }

    @Override
    public S isNotCloseTo() {
        return null;
    }

    @Override
    public S isNotBetween(A startInclusive, A endInclusive) {
        return null;
    }

    @Override
    public S isNotBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    protected abstract A zero();

    protected abstract A one();
}
