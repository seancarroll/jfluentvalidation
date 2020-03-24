package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.character.IsLowerCaseConstraint;
import jfluentvalidation.constraints.character.IsUpperCaseConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@link Character[]} typed subjects.
 *
 * @param <T>
 */
public class CharacterSubject<T> extends AbstractComparableSubject<CharacterSubject<T>, T, Character> {

    public CharacterSubject(PropertyRule<T, Character> rule) {
        super(CharacterSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public CharacterSubject<T> isLowerCase() {
        rule.addConstraint(new IsLowerCaseConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public CharacterSubject<T> isUpperCase() {
        rule.addConstraint(new IsUpperCaseConstraint<>());
        return myself;
    }

}
