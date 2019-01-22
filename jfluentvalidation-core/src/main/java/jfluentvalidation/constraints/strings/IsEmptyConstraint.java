package jfluentvalidation.constraints.strings;

import jfluentvalidation.constraints.Constraint;

public class IsEmptyConstraint implements Constraint<String> {

    @Override
    public boolean isValid(String instance) {
        return instance.isEmpty();
    }
}
