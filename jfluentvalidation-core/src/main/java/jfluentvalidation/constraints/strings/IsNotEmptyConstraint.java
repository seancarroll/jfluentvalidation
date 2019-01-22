package jfluentvalidation.constraints.strings;

import jfluentvalidation.constraints.Constraint;

public class IsNotEmptyConstraint implements Constraint<String> {

    @Override
    public boolean isValid(String instance) {
        return !instance.isEmpty();
    }
}
