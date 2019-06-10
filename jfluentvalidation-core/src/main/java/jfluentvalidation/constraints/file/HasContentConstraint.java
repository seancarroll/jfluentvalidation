package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.io.File;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> extends AbstractConstraint<T, File> {

    private final String expectedContent;
    private final String expectedCharset;

    public HasContentConstraint(String expectedContent, String expectedCharset) {
        super(DefaultMessages.FILE_HAS_CONTENT);
        this.expectedContent = expectedContent;
        this.expectedCharset = expectedCharset;
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        return false;
    }
}
