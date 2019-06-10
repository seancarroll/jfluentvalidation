package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasNameConstraint<T> extends AbstractConstraint<T, File> {

    private final String expected;

    public HasNameConstraint(String expected) {
        super(DefaultMessages.FILE_HAS_NAME);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        return expected.equals(context.getPropertyValue().getName());
    }
}
