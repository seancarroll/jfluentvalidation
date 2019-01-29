package jfluentvalidation.constraints.number;

import jfluentvalidation.constraints.Constraint;

import java.util.Objects;

// TODO: can we combine IsZero / IsOne / etc to use a single constraint that uses Objects.equals?
public class IsZeroConstraint implements Constraint<Number> {

    @Override
    public boolean isValid(Number instance) {
        return Objects.equals(instance, 0);
    }
}
