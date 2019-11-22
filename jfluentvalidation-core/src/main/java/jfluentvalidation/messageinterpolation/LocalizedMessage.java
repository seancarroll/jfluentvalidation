package jfluentvalidation.messageinterpolation;

import java.util.Locale;

public class LocalizedMessage {
    private final String message;
    private final Locale locale;

    public LocalizedMessage(String message, Locale locale) {
        this.message = message;
        this.locale = locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocalizedMessage that = (LocalizedMessage) o;
        if (!message.equals(that.message)) {
            return false;
        }

        return locale.equals(that.locale);
    }

    @Override
    public int hashCode() {
        int result = message.hashCode();
        result = 31 * result + locale.hashCode();
        return result;
    }
}

