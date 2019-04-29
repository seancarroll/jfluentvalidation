package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> implements Constraint<T, File> {

    private final String expectedContent;
    private final String expectedCharset;

    public HasContentConstraint(String expectedContent, String expectedCharset) {
        this.expectedContent = expectedContent;
        this.expectedCharset = expectedCharset;
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        return false;
    }
}
