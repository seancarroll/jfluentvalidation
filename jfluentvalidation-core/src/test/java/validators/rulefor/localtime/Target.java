package validators.rulefor.localtime;

import java.time.LocalTime;

class Target {

    private LocalTime time;

    public Target(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
}
