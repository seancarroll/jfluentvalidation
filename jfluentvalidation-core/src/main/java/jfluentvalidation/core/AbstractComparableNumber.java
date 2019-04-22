package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <S>
 * @param <A>
 */
public abstract class AbstractComparableNumber<S extends AbstractComparableNumber<S, A>, A extends Number & Comparable<? super A>>
    extends Subject<S, A>
    implements NumberSubject<S, A>, ComparableSubject<S, A> {

    public AbstractComparableNumber(Class<?> selfType, PropertyRule<?, A> rule) {
        super(selfType, rule);
    }

    @Override
    public S isEqualAccordingToCompareTo(A other) {
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint(other));
        return myself;
    }

    @Override
    public S isNotEqualAccordingToCompareTo(A other) {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint(other));
        return myself;
    }

    @Override
    public S isLessThan(A other) {
        rule.addConstraint(new IsLessThanConstraint(other));
        return myself;
    }

    @Override
    public S isLessThanOrEqualTo(A other) {
        rule.addConstraint(new IsLessThanOrEqualToConstraint(other));
        return myself;
    }

    @Override
    public S isGreaterThan(A other) {
        rule.addConstraint(new IsGreaterThanConstraint(other));
        return myself;
    }

    @Override
    public S isGreaterThanOrEqualTo(A other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint(other));
        return myself;
    }

    @Override
    public S isBetween(A startInclusive, A endInclusive) {
        rule.addConstraint(new IsBetweenConstraint(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public S isStrictlyBetween(A startExclusive, A endExclusive) {
        rule.addConstraint(new IsBetweenConstraint(startExclusive, endExclusive, true, true));
        return myself;
    }

    @Override
    public S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    public S isNotBetwen(A start, A end) {
        // TODO: should it be inclusive start and end?
        rule.addConstraint(new IsNotBetweenConstraint(start, end, true, true));
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
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint(0));
        return myself;
    }

    @Override
    public S isNotZero() {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint(0));
        return myself;
    }

    @Override
    public S isOne() {
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint(1));
        return myself;
    }

    @Override
    public S isNotOne() {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint(0));
        return myself;
    }

    @Override
    public S isPositive() {
        rule.addConstraint(new IsGreaterThanConstraint(0));
        return myself;
    }

    @Override
    public S isNotPositive() {
        rule.addConstraint(new IsLessThanOrEqualToConstraint(0));
        return myself;
    }

    @Override
    public S isNegative() {
        rule.addConstraint(new IsLessThanConstraint(0));
        return myself;
    }

    @Override
    public S isNotNegative() {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint(0));
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
}
