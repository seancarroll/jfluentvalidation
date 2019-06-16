package validators.rulefor.zoneddatetime;

import java.time.ZonedDateTime;

class Target {

    private ZonedDateTime dateTime;

    public Target(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
