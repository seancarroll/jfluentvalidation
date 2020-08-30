/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import jfluentvalidation.internal.Ensure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Forked from Hibernate Validator.
 */
class Token {

    private static final Pattern ESCAPED_OPENING_CURLY_BRACE = Pattern.compile("\\\\\\{");
    private static final Pattern ESCAPED_CLOSING_CURLY_BRACE = Pattern.compile("\\\\\\}");


    /**
     * Meta character to designate an EL expression.
     */
    private static final String EL_DESIGNATION_CHARACTER = "$";

    private static final String PARAMETER_DESIGNATION_CHARACTER = "{";

    private String value;
    private boolean isParameter;
    private boolean isEL;
    private boolean terminated;
    private StringBuilder builder;

    // tokenStart
    Token(String value) {
        this.value = Ensure.notNull(value);
        if (value.startsWith(EL_DESIGNATION_CHARACTER)) {
            isEL = true;
        } else if (value.startsWith(PARAMETER_DESIGNATION_CHARACTER)) {
            isParameter = true;
        }

        builder = new StringBuilder(value);
    }

    public Token(char tokenStart) {
        this(String.valueOf(tokenStart));
    }

    boolean isParameter() {
        return isParameter;
    }

    boolean isEL() {
        return isEL;
    }

    String getValue() {
        return value;
    }

    public void makeParameterToken() {
        isParameter = true;
    }

    public void makeELToken() {
        makeParameterToken();
        isEL = true;
    }

    public void terminate() {
        value = builder.toString();
        if (isEL) {
            Matcher matcher = ESCAPED_OPENING_CURLY_BRACE.matcher(value);
            value = matcher.replaceAll("{");

            matcher = ESCAPED_CLOSING_CURLY_BRACE.matcher(value);
            value = matcher.replaceAll("}");
        }
        builder = null;
        terminated = true;
    }

    public String getTokenValue() {
        if (!terminated) {
            throw new IllegalStateException("Trying to retrieve token value for unterminated token");
        }
        return value;
    }

    public void append(char character) {
        builder.append( character );
    }

    @Override
    public String toString() {
        return "Token{" + "value='" + value + '\'' +
            ", isEL=" + isEL +
            ", isParameter=" + isParameter +
            '}';
    }

//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder( "Token{" );
//        sb.append( "value='" ).append( value ).append( '\'' );
//        sb.append( ", terminated=" ).append( terminated );
//        sb.append( ", isEL=" ).append( isEL );
//        sb.append( ", isParameter=" ).append( isParameter );
//        sb.append( '}' );
//        return sb.toString();
//    }

}
