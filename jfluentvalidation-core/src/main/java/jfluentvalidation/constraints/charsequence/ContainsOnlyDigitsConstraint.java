package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

public class ContainsOnlyDigitsConstraint implements Constraint<CharSequence> {

    // TODO: null/empty
    @Override
    public boolean isValid(CharSequence value) {
        if (Strings.isNullOrEmpty(value)) {
            return false;
        }

        // instance.chars().allMatch(Character::isDigit)

        // TODO: should we return index of non-digit characters?
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
