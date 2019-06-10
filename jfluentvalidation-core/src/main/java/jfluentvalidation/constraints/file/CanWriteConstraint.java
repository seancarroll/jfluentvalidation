package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class CanWriteConstraint<T> extends AbstractConstraint<T, File> {

    public CanWriteConstraint() {
        super(DefaultMessages.FILE_CAN_WRITE);
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        return context.getPropertyValue().canWrite();
    }

}
