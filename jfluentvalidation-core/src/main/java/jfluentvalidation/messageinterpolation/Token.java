package jfluentvalidation.messageinterpolation;

import jfluentvalidation.internal.Ensure;

public class Token {

    /**
     * Meta character to designate an EL expression.
     */
    private static final String EL_DESIGNATION_CHARACTER = "$";

    private static final String PARAMETER_DESIGNATION_CHARACTER = "{";

    private boolean isParameter;
    private boolean isEL;
    private String value;

    Token(String value) {
        this.value = Ensure.notNull(value);
        if (value.startsWith(EL_DESIGNATION_CHARACTER)) {
            isEL = true;
        } else if (value.startsWith(PARAMETER_DESIGNATION_CHARACTER)) {
            isParameter = true;
        }
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

    @Override
    public String toString() {
        return "Token{" + "value='" + value + '\'' +
            ", isEL=" + isEL +
            ", isParameter=" + isParameter +
            '}';
    }

}
