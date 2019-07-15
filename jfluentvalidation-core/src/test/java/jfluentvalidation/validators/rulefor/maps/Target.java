package jfluentvalidation.validators.rulefor.maps;

import java.util.Map;

public class Target {

    private final Map<String, String> map;

    public Target(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
