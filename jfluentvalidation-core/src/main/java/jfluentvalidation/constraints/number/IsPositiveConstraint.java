package jfluentvalidation.constraints.number;

import jfluentvalidation.constraints.Constraint;

// TODO: this seems to be a good one for comparable that is used across multiple constraints
public class IsPositiveConstraint implements Constraint<Number> {

    @Override
    public boolean isValid(Number instance) {
        // TODO: cant do this because Number does not implement comparble
        // return instance.compareTo(other) > 0;;
        return false;
    }
}
