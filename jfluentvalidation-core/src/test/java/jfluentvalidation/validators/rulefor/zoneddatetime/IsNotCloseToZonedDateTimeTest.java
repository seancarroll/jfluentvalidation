package jfluentvalidation.validators.rulefor.zoneddatetime;

import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;

class IsNotCloseToZonedDateTimeTest extends AbstractZonedDateTime {

    IsNotCloseToZonedDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 8, 0, 0, 0,
            TZ_CHICAGO));
    }
}
