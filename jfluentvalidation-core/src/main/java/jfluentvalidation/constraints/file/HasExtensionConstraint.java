package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasExtensionConstraint<T> extends AbstractConstraint<T, File> {

    private final String expected;

    public HasExtensionConstraint(@Nonnull String expected) {
        super(DefaultMessages.FILE_HAS_EXTENSION);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, File> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // TODO: check if not a file. Should we throw?
        if (!context.getPropertyValue().isFile()) {
            return false;
        }

        String actualExtension = getFileExtension(context.getPropertyValue());
        return expected.equals(actualExtension);
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int dotAt = name.lastIndexOf('.');
        return (dotAt == -1) ? null : name.substring(dotAt + 1);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, File> context) {
        context.getMessageContext().appendArgument("extension", expected);
    }
}
