package jfluentvalidation.constraints.strings;

import jfluentvalidation.constraints.Constraint;

public class LessThanConstraint implements Constraint<String> {

    private final String value;

    public LessThanConstraint(String value) {
        this.value = value;
    }

    @Override
    public boolean isValid(String instance) {
        return instance.compareTo(value) < 0;
    }
}
