package jfluentvalidation.internal;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.ZonedDateTime;

public class FixedClockProvider implements ClockProvider {

    private Clock clock;

    public FixedClockProvider(ZonedDateTime dateTime) {
        clock = Clock.fixed(dateTime.toInstant(), dateTime.getZone());
    }

    @Override
    public Clock getClock() {
        return clock;
    }

}
