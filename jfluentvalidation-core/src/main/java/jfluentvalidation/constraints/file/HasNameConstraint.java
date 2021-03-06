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
public class HasNameConstraint<T> extends AbstractConstraint<T, File> {

    private final String expected;

    public HasNameConstraint(@Nonnull String expected) {
        super(DefaultMessages.FILE_HAS_NAME);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, File> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return expected.equals(context.getPropertyValue().getName());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, File> context) {
        context.getMessageContext().appendArgument("name", expected);
    }
}
