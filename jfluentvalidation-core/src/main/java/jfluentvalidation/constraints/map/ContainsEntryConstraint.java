package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

public class ContainsEntryConstraint implements Constraint<Map<?,?>> {
    @Override
    public boolean isValid(Map<?, ?> value) {
        return false;
    }
}
