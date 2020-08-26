package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class FileExistsConstraint<T> extends AbstractConstraint<T, File> {

    public FileExistsConstraint() {
        super(DefaultMessages.FILE_EXISTS);
    }

    @Override
    public boolean isValid(ConstraintContext<T, File> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().exists();
    }

}
