package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

// https://github.com/hibernate/hibernate-validator/blob/eaa2cb6e45f95007c18297c2e4faea33e1c6b5ab/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/AbstractEmailValidator.java
// https://github.com/apache/commons-validator/blob/8d0b6a146f9a60339b4db581b7697deaaf1b252b/src/main/java/org/apache/commons/validator/routines/EmailValidator.java
// fluentvalidators EmailValidator
// not comprehensive but good enough
public class IsEmailConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence instance) {
        return false;
    }
}
