package jfluentvalidation.validators.rulefor.file;

import java.io.File;

class Target {

    private final File file;

    public Target(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
