package validators.rulefor.localtime;

import java.time.LocalTime;

class TestTarget {

    private LocalTime time;

    public TestTarget(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
}
