package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class ContainsOnlyDigitsConstraint implements Constraint<CharSequence> {

    // TODO: null/empty
    @Override
    public boolean isValid(CharSequence instance) {
//        if (isEmpty(cs)) {
//            return false;
//        }
        for (int i = 0; i < instance.length(); i++) {
            if (!Character.isDigit(instance.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
