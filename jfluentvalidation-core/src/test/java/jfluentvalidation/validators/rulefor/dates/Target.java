package jfluentvalidation.validators.rulefor.dates;

import javax.validation.constraints.FutureOrPresent;
import java.util.Date;

class Target {

    @FutureOrPresent
    private Date date;

    public Target(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
