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
    extends AbstractComparableSubject<S, T, A>
    implements NumberSubject<S, A> {

    public AbstractComparableNumber(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
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

    protected abstract A zero();

    protected abstract A one();
}
