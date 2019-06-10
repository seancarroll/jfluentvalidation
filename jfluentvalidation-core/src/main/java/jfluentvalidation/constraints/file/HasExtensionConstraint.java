package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasExtensionConstraint<T> extends AbstractConstraint<T, File> {

    private final String expected;

    public HasExtensionConstraint(String expected) {
        super(DefaultMessages.FILE_HAS_EXTENSION);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        String actualExtension = getFileExtension(context.getPropertyValue());
        return expected.equals(actualExtension);
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int dotAt = name.lastIndexOf('.');
        return (dotAt == -1) ? null : name.substring(dotAt + 1);
    }

}
