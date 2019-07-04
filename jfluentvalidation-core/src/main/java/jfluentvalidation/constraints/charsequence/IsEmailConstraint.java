package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import org.hibernate.validator.internal.util.DomainNameUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

// https://github.com/hibernate/hibernate-validator/blob/eaa2cb6e45f95007c18297c2e4faea33e1c6b5ab/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/AbstractEmailValidator.java
// https://github.com/apache/commons-validator/blob/8d0b6a146f9a60339b4db581b7697deaaf1b252b/src/main/java/org/apache/commons/validator/routines/EmailValidator.java
// fluentvalidators EmailValidator
// not comprehensive but good enough

// This code is from Hibernate validator. https://github.com/hibernate/hibernate-validator/blob/master/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/AbstractEmailValidator.java
// TODO: how to legally use hibernate validator email and DomainNameUtil logic?
// Make sure I'm not violating the license

/**
 * Checks that the given {@code CharSequence} being validated is a valid email.
 * Note: This validation should not be considered a fully comprehensive check but viewed as a "good enough" for must
 * scenarios.
 * You should use alternative means to actually determine if given {@code CharSequence} is actually valid email.
 *
 * @param <T> type of instance to validate.
 */
public class IsEmailConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private static final int MAX_LOCAL_PART_LENGTH = 64;

    private static final String LOCAL_PART_ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~\u0080-\uFFFF-]";
    private static final String LOCAL_PART_INSIDE_QUOTES_ATOM = "([a-z0-9!#$%&'*.(),<>\\[\\]:;  @+/=?^_`{|}~\u0080-\uFFFF-]|\\\\\\\\|\\\\\\\")";

    /**
     * Regular expression for the local part of an email address (everything before '@')
     */
    private static final Pattern LOCAL_PART_PATTERN = Pattern.compile(
        "(" + LOCAL_PART_ATOM + "+|\"" + LOCAL_PART_INSIDE_QUOTES_ATOM + "+\")" +
            "(\\." + "(" + LOCAL_PART_ATOM + "+|\"" + LOCAL_PART_INSIDE_QUOTES_ATOM + "+\")" + ")*", CASE_INSENSITIVE
    );

    public IsEmailConstraint() {
        super(DefaultMessages.CHARSEQUENCE_IS_EMAIL);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        CharSequence value = context.getPropertyValue();
        // hibernate validator return true here
        if (value == null || value.length() == 0) {
            return false;
        }

        // cannot split email string at @ as it can be a part of quoted local part of email.
        // so we need to split at a position of last @ present in the string:
        String stringValue = value.toString();
        int splitPosition = stringValue.lastIndexOf('@');

        // need to check if
        if (splitPosition < 0) {
            return false;
        }

        String localPart = stringValue.substring(0, splitPosition);
        String domainPart = stringValue.substring(splitPosition + 1);

        if (!isValidEmailLocalPart(localPart)) {
            return false;
        }

        return DomainNameUtil.isValidEmailDomainAddress(domainPart);
    }

    private boolean isValidEmailLocalPart(String localPart) {
        if (localPart.length() > MAX_LOCAL_PART_LENGTH) {
            return false;
        }
        Matcher matcher = LOCAL_PART_PATTERN.matcher(localPart);
        return matcher.matches();
    }
}
