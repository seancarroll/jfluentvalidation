package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

public class ContainsOnlyWhitespaces implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return Strings.isNullOrEmpty(value) && value.chars().allMatch(Character::isWhitespace);
    }
}
