package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.regex.Pattern;

// https://github.com/hibernate/hibernate-validator/blob/eaa2cb6e45f95007c18297c2e4faea33e1c6b5ab/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/AbstractEmailValidator.java
// https://github.com/apache/commons-validator/blob/8d0b6a146f9a60339b4db581b7697deaaf1b252b/src/main/java/org/apache/commons/validator/routines/EmailValidator.java
// fluentvalidators EmailValidator
// not comprehensive but good enough

/**
 * Checks that the given {@code CharSequence} being validated is a valid email.
 * Note: This validation should not be considered a fully comprehensive check but viewed as a "good enough" for must
 * scenarios.
 * You should use alternative means to actually determine if given {@code CharSequence} is actually valid email.
 *
 * @param <T>  type of instance to validate.
 */
public class IsEmailConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    // TODO: do we want the ability for users to override and if so how?
    // Possible suggestions are
    // allow users to pass in a pattern here
    // allow users to set it as part of a validator configuration option or perhaps even a global configuration across all validators
    // users could create their own constraint or validator and pass it in when creating the Validator
    private static final Pattern DEFAULT_EMAIL = Pattern.compile("");

    public IsEmailConstraint() {
        super(DefaultMessages.CHARSEQUENCE_IS_EMAIL);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return false;
    }
}
