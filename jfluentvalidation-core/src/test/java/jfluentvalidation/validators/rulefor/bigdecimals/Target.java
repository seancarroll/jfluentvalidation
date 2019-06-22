package jfluentvalidation.validators.rulefor.bigdecimals;

import java.math.BigDecimal;

public class Target {

    private final BigDecimal number;

    public Target(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }
}
