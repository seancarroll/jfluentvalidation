package jfluentvalidation.constraints.inputstream;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Objects;

import static jfluentvalidation.common.InputStreams.readLines;

public class HasSameContentAsConstraint<T> extends AbstractConstraint<T, InputStream> {

    private final InputStream expected;

    public HasSameContentAsConstraint(@Nonnull InputStream expected) {
        super(DefaultMessages.INPUTSTREAM_HAS_SAME_CONTENT_AS);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, InputStream> context) {
        try {
            if (context.getPropertyValue() == null) {
                return true;
            }

            // TODO: should we diff so that we can provide to caller?
            List<String> actualLines = readLines(context.getPropertyValue());
            List<String> expectedLines = readLines(expected);
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
            throw new UncheckedIOException("Unable to read contents of InputStream", e);
        }
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, InputStream> context) {
        context.getMessageContext().appendArgument("content", expected);
    }
}
