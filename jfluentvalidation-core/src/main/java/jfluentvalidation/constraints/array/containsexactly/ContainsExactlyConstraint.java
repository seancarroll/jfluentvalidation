package jfluentvalidation.constraints.array.containsexactly;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// TODO: have a base class for array, iterable, map???
public class ContainsExactlyConstraint<T, A> extends AbstractConstraint<T, A> {

    private final List<?> expected;

    public ContainsExactlyConstraint(List<?> expected) {
        // TODO: fix message name
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY);
        this.expected =  Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        List actualAsList = convertToList(context.getPropertyValue());
        IterableDifference<?> diff = IterableDifference.diff(actualAsList, expected);
        if (!diff.differencesFound()) {
            // actual and values have the same elements but are they in the same order ?
            for (int i = 0; i < actualAsList.size(); i++) {
                Object actualElement = actualAsList.get(i);
                Object expectedElement = expected.get(i);
                if (!Objects.equals(actualElement, expectedElement)) {
                    context.getMessageContext().appendArgument("differentOrderElement", actualElement);
                    context.getMessageContext().appendArgument("indexOfDifferentElement", i);
                    context.getMessageContext().appendArgument("missingValues", null);
                    context.getMessageContext().appendArgument("unexpectedValues", null);
                    return false;
                }
            }
            return true;
        }

        // need to set all values to avoid UnresolvablePropertyException when evaluating validation message
        context.getMessageContext().appendArgument("differentOrderElement", null);
        context.getMessageContext().appendArgument("indexOfDifferentElement", null);
        context.getMessageContext().appendArgument("missingValues", diff.getMissing().isEmpty() ? null : diff.getMissing());
        context.getMessageContext().appendArgument("unexpectedValues", diff.getUnexpected().isEmpty() ? null : diff.getUnexpected());

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
        } else if (clazz == long.class) {
            return Longs.asList((long[])o);
        } else if (clazz == short.class) {
            return Shorts.asList((short[])o);
        } else if (clazz == boolean.class) {
            return Booleans.asList((boolean[])o);
        }

        throw new RuntimeException("could not convert object to list");
    }
}
