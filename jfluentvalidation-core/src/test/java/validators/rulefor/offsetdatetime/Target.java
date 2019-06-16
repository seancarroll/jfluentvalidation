package validators.rulefor.offsetdatetime;

import java.time.OffsetDateTime;

class Target {

    private OffsetDateTime dateTime;

    public Target(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }
}
