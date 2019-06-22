package jfluentvalidation.validators.rulefor.bigintegers;

import java.math.BigInteger;

public class Target {

    private final BigInteger number;

    public Target(BigInteger number) {
        this.number = number;
    }

    public BigInteger getNumber() {
        return number;
    }
}
