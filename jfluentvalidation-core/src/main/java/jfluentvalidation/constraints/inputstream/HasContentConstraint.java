package jfluentvalidation.constraints.inputstream;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> extends AbstractConstraint<T, InputStream> {

    private final String expected;

    public HasContentConstraint(String expected) {
        super(DefaultMessages.INPUTSTREAM_HAS_CONTENT);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, InputStream> context) {
        try {
            List<String> actualLines = linesFromBufferedReader(new BufferedReader(new InputStreamReader(context.getPropertyValue(), Charset.defaultCharset())));
            List<String> expectedExpected = linesFromBufferedReader(new BufferedReader(new StringReader(expected)));
            // TODO: implement
            // return expected.equals(context.getPropertyValue().)

        } catch (IOException e) {
            // String msg = format("Unable to compare contents of InputStream:%n  <%s>%nand String:%n  <%s>", actual, expected);
            // TODO:
            // throw new InputStreamsException(msg, e);
        } finally {

        }

        return false;
    }

    private List<String> linesFromBufferedReader(BufferedReader reader) throws IOException {
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
