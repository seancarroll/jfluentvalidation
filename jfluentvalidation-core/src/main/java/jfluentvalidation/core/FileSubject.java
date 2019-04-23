package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.io.File;

public class FileSubject<T> extends Subject<FileSubject<T>, T, File> {

    public FileSubject(PropertyRule<T, File> rule) {
        super(FileSubject.class, rule);
    }

}
