package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

import java.util.Objects;

public class IsNotEqualsConstraint implements Constraint<Object> {

    private final Object other;

    public IsNotEqualsConstraint(Object other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Object value) {
        return !Objects.equals(value, other);
    }
}
