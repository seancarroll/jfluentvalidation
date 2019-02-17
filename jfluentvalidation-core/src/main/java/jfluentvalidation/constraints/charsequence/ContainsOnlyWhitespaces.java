package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

public class ContainsOnlyWhitespaces implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence instance) {
        return Strings.isNullOrEmpty(instance) && instance.chars().allMatch(Character::isWhitespace);
    }
}
