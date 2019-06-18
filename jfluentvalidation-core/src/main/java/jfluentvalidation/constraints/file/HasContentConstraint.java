package jfluentvalidation.constraints.file;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> extends AbstractConstraint<T, File> {

    protected final Logger LOGGER = LoggerFactory.getLogger(HasContentConstraint.class);

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

            // TODO: check if not a file. Should we throw?
            if (!context.getPropertyValue().isFile()) {
                return false;
            }

            // BufferedReader reader = Files.newBufferedReader(context.getPropertyValue().toPath(), Charset.defaultCharset());
            byte[] bytes = Files.readAllBytes(context.getPropertyValue().toPath());
            return expectedContent.equals(new String(bytes, expectedCharset));

        } catch (IOException e) {
            LOGGER.warn("encountered an IOException while reading file {}", context.getPropertyValue().getName());
            return false;
        }
    }
}
