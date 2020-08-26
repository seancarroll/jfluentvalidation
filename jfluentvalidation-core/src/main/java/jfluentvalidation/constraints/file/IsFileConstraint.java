package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsFileConstraint<T> extends AbstractConstraint<T, File> {

    public IsFileConstraint() {
        super(DefaultMessages.FILE_IS_FILE);
    }

    @Override
    public boolean isValid(ConstraintContext<T, File> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isFile();
    }

}
