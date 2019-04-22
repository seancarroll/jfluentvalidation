package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.io.File;

public class FileSubject extends Subject<FileSubject, File> {

    public FileSubject(PropertyRule<?, File> rule) {
        super(FileSubject.class, rule);
    }


}
