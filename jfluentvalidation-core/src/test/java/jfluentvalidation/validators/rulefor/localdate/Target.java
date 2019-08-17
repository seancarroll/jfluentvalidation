package jfluentvalidation.validators.rulefor.localdate;

import java.time.LocalDate;

class Target {

    private LocalDate date;

    public Target(LocalDate birthday) {
        this.date = birthday;
    }

    public LocalDate getDate() {
        return date;
    }
}
