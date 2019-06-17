package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class CanReadConstraint<T> extends AbstractConstraint<T, File> {

    public CanReadConstraint() {
        super(DefaultMessages.FILE_CAN_READ);
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().canRead();
    }

}
