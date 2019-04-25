package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.io.File;

<<<<<<< HEAD
public class FileSubject extends Subject<FileSubject, File> {

    public FileSubject(PropertyRule<?, File> rule) {
        super(FileSubject.class, rule);
    }

=======
public class FileSubject<T> extends Subject<FileSubject<T>, T, File> {

    public FileSubject(PropertyRule<T, File> rule) {
        super(FileSubject.class, rule);
    }
>>>>>>> 635fe42bc2670285af08a9b670987c3a59c5adb0

}
