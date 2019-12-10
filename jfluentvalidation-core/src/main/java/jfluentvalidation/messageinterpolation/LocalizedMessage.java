package jfluentvalidation.messageinterpolation;

import com.google.common.base.Objects;

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
        return Objects.hashCode(message, locale);
    }

    @Override
    public String toString() {
        return "LocalizedMessage{" +
            "message='" + message + '\'' +
            ", locale=" + locale +
            '}';
    }
}

