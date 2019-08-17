package jfluentvalidation.validators.rulefor.inputstream;

import java.io.InputStream;

class Target {

    private final InputStream inputStream;

    public Target(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
