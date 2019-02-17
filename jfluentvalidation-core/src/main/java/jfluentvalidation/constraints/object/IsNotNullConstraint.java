package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

public class IsNotNullConstraint implements Constraint<Object> {

    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}
