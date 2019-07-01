package jfluentvalidation.constraints.file;

import jfluentvalidation.IORuntimeException;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static java.lang.String.format;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> extends AbstractConstraint<T, File> {

    private final String expectedContent;
    private final Charset expectedCharset;

    public HasContentConstraint(@Nonnull String expectedContent, @Nonnull Charset expectedCharset) {
        super(DefaultMessages.FILE_HAS_CONTENT);
        this.expectedContent = Ensure.notNull(expectedContent);
        this.expectedCharset = Ensure.notNull(expectedCharset);
    }

    @Override
    public boolean isValid(RuleContext<T, File> context) {
        try {
            if (context.getPropertyValue() == null) {
                return false;
            }

            // TODO: Should we throw?
            if (!context.getPropertyValue().isFile()) {
                return false;
            }

            // BufferedReader reader = Files.newBufferedReader(context.getPropertyValue().toPath(), Charset.defaultCharset());
            byte[] bytes = Files.readAllBytes(context.getPropertyValue().toPath());
            return expectedContent.equals(new String(bytes, expectedCharset));

        } catch (IOException e) {
            String msg = format("Unable to read contents of File %s", context.getPropertyValue().getName());
            throw new IORuntimeException(msg, e);
        }
    }
}
