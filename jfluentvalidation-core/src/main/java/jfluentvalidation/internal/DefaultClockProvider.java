package jfluentvalidation.internal;

import javax.validation.ClockProvider;
import java.time.Clock;

/**
 * A default {@link ClockProvider} implementation which returns the current system time in the default time zone using
 * {@link Clock#systemDefaultZone()}.
 * <p>
 *
 * From hibernate-validator
 */
public class DefaultClockProvider implements ClockProvider {

    public static final DefaultClockProvider INSTANCE = new DefaultClockProvider();

    private DefaultClockProvider() {
    }

    @Override
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }

}

