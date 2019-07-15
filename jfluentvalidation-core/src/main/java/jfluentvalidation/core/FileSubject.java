package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.comparable.*;
import jfluentvalidation.constraints.file.CanReadConstraint;
import jfluentvalidation.constraints.file.CanWriteConstraint;
import jfluentvalidation.constraints.file.FileExistsConstraint;
import jfluentvalidation.constraints.file.HasContentConstraint;
import jfluentvalidation.constraints.file.HasExtensionConstraint;
import jfluentvalidation.constraints.file.HasNameConstraint;
import jfluentvalidation.constraints.file.IsAbsoluteConstraint;
import jfluentvalidation.constraints.file.IsDirectoryConstraint;
import jfluentvalidation.constraints.file.IsFileConstraint;
import jfluentvalidation.constraints.file.IsRelativeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.io.File;
import java.nio.charset.Charset;

/**
 *
 * @param <T>  the type of the instance
 * @see java.io.File
 */
public class FileSubject<T> extends Subject<FileSubject<T>, T, File> implements ComparableSubject<FileSubject<T>, T, File> {

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

    @Override
    public FileSubject<T> isEqualAccordingToCompareTo(File other) {
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isNotEqualAccordingToCompareTo(File other) {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isLessThan(File other) {
        rule.addConstraint(new IsLessThanConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isLessThanOrEqualTo(File other) {
        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isGreaterThan(File other) {
        rule.addConstraint(new IsGreaterThanConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isGreaterThanOrEqualTo(File other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isBetween(File startInclusive, File endInclusive) {
        rule.addConstraint(new IsBetweenConstraint<>(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public FileSubject<T> isStrictlyBetween(File startExclusive, File endExclusive) {
        rule.addConstraint(new IsBetweenConstraint<>(startExclusive, endExclusive, true, true));
        return myself;
    }

    @Override
    public FileSubject<T> isBetween(File start, File end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public FileSubject<T> isNotBetween(File startInclusive, File endInclusive) {
        rule.addConstraint(new IsNotBetweenConstraint<>(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public FileSubject<T> isNotBetween(File start, File end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsNotBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
