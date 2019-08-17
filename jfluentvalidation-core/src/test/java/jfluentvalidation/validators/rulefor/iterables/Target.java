package jfluentvalidation.validators.rulefor.iterables;

import java.util.List;

class Target {
    private final List<String> value;

    public Target(List<String> value) {
        this.value = value;
    }

    public List<String> getValue() {
        return value;
    }
}
