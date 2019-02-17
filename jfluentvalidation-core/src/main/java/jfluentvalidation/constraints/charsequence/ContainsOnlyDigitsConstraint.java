package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

public class ContainsOnlyDigitsConstraint implements Constraint<CharSequence> {

    // TODO: null/empty
    @Override
    public boolean isValid(CharSequence instance) {
        if (Strings.isNullOrEmpty(instance)) {
            return false;
        }

        // instance.chars().allMatch(Character::isDigit)

        // TODO: should we return index of non-digit characters?
        for (int i = 0; i < instance.length(); i++) {
            if (!Character.isDigit(instance.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
