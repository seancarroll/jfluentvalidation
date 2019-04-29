package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasNameConstraint<T> implements Constraint<T, File> {

    private final String expected;

    public HasNameConstraint(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        return expected.equals(context.getPropertyValue().getName());
    }
}
