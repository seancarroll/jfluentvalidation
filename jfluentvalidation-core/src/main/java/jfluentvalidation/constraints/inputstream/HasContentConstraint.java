package jfluentvalidation.constraints.inputstream;

import jfluentvalidation.IORuntimeException;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                return false;
            }

            // TODO: should we diff so that we can provide to caller?
            List<String> actualLines = linesFromBufferedReader(new BufferedReader(new InputStreamReader(context.getPropertyValue())));
            List<String> expectedLines = linesFromBufferedReader(new BufferedReader(new StringReader(expected)));
            if (actualLines.size() != expectedLines.size()) {
                return false;
            }

            for (int i = 0; i < actualLines.size(); i++) {
                if (!Objects.equals(actualLines.get(i), expectedLines.get(0))) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            throw new IORuntimeException("Unable to read contents of InputStream", e);
        }
    }

    private List<String> linesFromBufferedReader(BufferedReader reader) throws IOException {
        try {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } finally {
            // TODO: move to utility method?
            if (reader != null) {
                reader.close();
            }
        }
    }
}
