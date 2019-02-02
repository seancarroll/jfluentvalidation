package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

import java.util.Objects;

// TODO: difference between various equals
// 1. ==
// 2. Object.equals
// 3. comparesTo equal to 0
public class IsEqualsConstraint<S> implements Constraint<S> {

    private final S other;

    public IsEqualsConstraint(S other) {
        this.other = other;
    }

    @Override
    public boolean isValid(S instance) {
        return Objects.equals(instance, other);
    }
}
