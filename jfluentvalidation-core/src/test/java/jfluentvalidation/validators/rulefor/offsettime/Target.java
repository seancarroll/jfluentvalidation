package jfluentvalidation.validators.rulefor.offsettime;

import java.time.OffsetTime;

class Target {

    private OffsetTime time;

    public Target(OffsetTime time) {
        this.time = time;
    }

    public OffsetTime getTime() {
        return time;
    }
}
