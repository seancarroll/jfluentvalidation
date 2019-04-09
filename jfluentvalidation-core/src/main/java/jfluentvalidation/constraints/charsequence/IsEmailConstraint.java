package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.regex.Pattern;

// https://github.com/hibernate/hibernate-validator/blob/eaa2cb6e45f95007c18297c2e4faea33e1c6b5ab/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/AbstractEmailValidator.java
// https://github.com/apache/commons-validator/blob/8d0b6a146f9a60339b4db581b7697deaaf1b252b/src/main/java/org/apache/commons/validator/routines/EmailValidator.java
// fluentvalidators EmailValidator
// not comprehensive but good enough
public class IsEmailConstraint<T> implements Constraint<T, CharSequence> {

    // TODO: do we want the ability for users to override and if so how?
    // Possible suggestionss are
    // allow users to pass in a pattern here
    // allow users to set it as part of a validator configuration option or perhaps even a global configuration across all validators
    // users could create their own constraint or validator and pass it in when creating the Validator
    private static final Pattern DEFAULT_EMAIL = Pattern.compile("");

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return false;
    }
}
