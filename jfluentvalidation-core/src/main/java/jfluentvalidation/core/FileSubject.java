package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.file.*;
import jfluentvalidation.rules.PropertyRule;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Constraints for {@link File} subjects.
 *
 * @param <T>  the type of the instance
 * @see java.io.File
 */
public class FileSubject<T> extends AbstractComparableSubject<FileSubject<T>, T, File> {

    public FileSubject(PropertyRule<T, File> rule) {
        super(FileSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public FileSubject<T> canRead() {
        rule.addConstraint(new CanReadConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> canWrite() {
        rule.addConstraint(new CanWriteConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> exists() {
        rule.addConstraint(new FileExistsConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> hasContent(String content) {
        return hasContent(content, Charset.defaultCharset());
    }

    @CanIgnoreReturnValue
    public FileSubject<T> hasContent(String content, Charset charset) {
        rule.addConstraint(new HasContentConstraint<>(content, charset));
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> hasExtension(String extension) {
        rule.addConstraint(new HasExtensionConstraint<>(extension));
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> hasName(String name) {
        rule.addConstraint(new HasNameConstraint<>(name));
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> isAbsolute() {
        rule.addConstraint(new IsAbsoluteConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> isDirectory() {
        rule.addConstraint(new IsDirectoryConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> isFile() {
        rule.addConstraint(new IsFileConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public FileSubject<T> isRelative() {
        rule.addConstraint(new IsRelativeConstraint<>());
        return myself;
    }
}
