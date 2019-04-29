package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsDirectoryConstraint<T> implements Constraint<T, File> {

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        return context.getPropertyValue().isDirectory();
    }

}
