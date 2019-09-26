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

    public Token(String value) {
        this.value = Ensure.notNull(value);
        if (value.startsWith(EL_DESIGNATION_CHARACTER)) {
            isEL = true;
        } else if (value.startsWith(PARAMETER_DESIGNATION_CHARACTER)) {
            isParameter = true;
        }
    }

    public boolean isParameter() {
        return isParameter;
    }

    public boolean isEL() {
        return isEL;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{");
        sb.append("value='").append(value).append('\'');
        sb.append(", isEL=").append(isEL);
        sb.append(", isParameter=").append(isParameter);
        sb.append('}');
        return sb.toString();
    }

}
