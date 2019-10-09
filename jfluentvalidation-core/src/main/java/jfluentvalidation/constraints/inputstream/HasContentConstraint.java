package jfluentvalidation.constraints.inputstream;

import jfluentvalidation.IORuntimeException;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Objects;

import static jfluentvalidation.common.InputStreams.readLines;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> extends AbstractConstraint<T, InputStream> {

    private final String expected;

    public HasContentConstraint(@Nonnull String expected) {
        super(DefaultMessages.INPUTSTREAM_HAS_CONTENT);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, InputStream> context) {
        try {
            if (context.getPropertyValue() == null) {
                return true;
            }

            // TODO: should we diff so that we can provide to caller?
            List<String> actualLines = readLines(context.getPropertyValue());
            List<String> expectedLines = readLines(new BufferedReader(new StringReader(expected)));
            if (actualLines.size() != expectedLines.size()) {
                return false;
            }

            for (int i = 0; i < actualLines.size(); i++) {
                if (!Objects.equals(actualLines.get(i), expectedLines.get(i))) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            throw new IORuntimeException("Unable to read contents of InputStream", e);
        }
    }

    @Override
    public void addParametersToContext(RuleContext<T, InputStream> context) {
        context.getMessageContext().appendArgument("content", expected);
    }
}
