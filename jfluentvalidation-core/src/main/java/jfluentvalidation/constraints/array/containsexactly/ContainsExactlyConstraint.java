package jfluentvalidation.constraints.array.containsexactly;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.common.Lists;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ContainsExactlyConstraint<T, A> extends AbstractConstraint<T, A> {

    private final List expected;

    public ContainsExactlyConstraint(Iterable<?> expected) {
        // TODO: fix message name
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY_ELEMENTS_IN);
        this.expected =  Lists.newArrayList(Ensure.notNull(expected));
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        List actualAsList = convertToList(context.getPropertyValue());
        IterableDifference<?> diff = IterableDifference.diff(expected, actualAsList);
        if (!diff.differencesFound()) {
            // actual and values have the same elements but are they in the same order ?
            for (int i = 0; i < actualAsList.size(); i++) {
                Object actualElement = actualAsList.get(i);
                Object expectedElement = expected.get(i);
                if (!Objects.equals(actualElement, expectedElement)) {
                    context.getMessageContext().appendArgument("differentOrder", actualElement);
                    return false;
                }
            }
            return true;
        }

        if (diff.hasMissing()) {
            context.getMessageContext().appendArgument("missingValues", diff.getMissing());
        }

        if (diff.hasUnexpected()) {
            context.getMessageContext().appendArgument("unexpectedValues", diff.getUnexpected());
        }

        return false;
    }

    private List<?> convertToList(Object o) {
        Ensure.isArray(o, "object must be an array");

       Class<?> clazz = o.getClass().getComponentType();

       if (!clazz.isPrimitive()) {
           return Arrays.asList((Object[])o);
       }

        if (clazz == byte.class) {
            return Bytes.asList((byte[])o);
        } else if (clazz == char.class) {
            return Chars.asList((char[])o);
        } else if (clazz == double.class) {
            return Doubles.asList((double[])o);
        } else if (clazz == float.class) {
            return Floats.asList((float[])o);
        } else if (clazz == int.class) {
            return Ints.asList((int[])o);
        } else if (clazz == short.class) {
            return Shorts.asList((short[])o);
        } else if (clazz == boolean.class) {
            return Booleans.asList((boolean[])o);
        }

        throw new RuntimeException("could not convert object to list");
    }
}
