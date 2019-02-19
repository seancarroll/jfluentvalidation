package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

// TODO: do we need this? Could we just use the Object IsNotNullConstraint?
public class IsNotNullConstraint implements Constraint<Map<?,?>> {
    @Override
    public boolean isValid(Map<?, ?> value) {
        return false;
    }
}
