package jfluentvalidation.validators.rulefor.localdatetime;

import java.time.LocalDateTime;

class Target {

    private LocalDateTime dateTime;

    public Target(LocalDateTime birthday) {
        this.dateTime = birthday;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
