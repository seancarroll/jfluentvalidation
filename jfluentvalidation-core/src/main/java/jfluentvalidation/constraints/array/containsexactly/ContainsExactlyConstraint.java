package jfluentvalidation.constraints.array.containsexactly;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.reflect.Array.getLength;
import static java.util.Collections.unmodifiableList;

public class ContainsExactlyConstraint<T, A> extends AbstractConstraint<T, A> {

     private final Object[] expected;
//    private final List<?> expected;

    public ContainsExactlyConstraint(Object expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY_ELEMENTS_IN);
        this.expected = (Object[])Ensure.isArray(expected, "expected");
//        this.expected = convertToList(Ensure.isArray(expected, "expected"));
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

//        List<Object> actualAsList = asList(context.getPropertyValue());
//        IterableDifference<Object> diff = IterableDifference.diff(asList(expected), actualAsList);

//        System.out.println("---------");
//        System.out.println(context.getPropertyValue().getClass());
//        System.out.println(context.getPropertyValue().getClass().getComponentType());

        // Object[] actualAsArray = (Object[])context.getPropertyValue();
        //Difference diff = Difference.diff(expected, actualAsArray);
        List actualAsList = convertToList(context.getPropertyValue());
        IterableDifference<Object> diff = IterableDifference.diff(Arrays.asList(expected), actualAsList);
        if (!diff.differencesFound()) {
            // actual and values have the same elements but are they in the same order ?
            // int arrayLength = MoreArrays.len(actual);
            for (int i = 0; i < actualAsList.size(); i++) {
                // Object actualElement = Array.get(actual, i);
                Object actualElement = actualAsList.get(i);
                // Object expectedElement = Array.get(values, i);
                Object expectedElement = Array.get(expected, i);
                // if (!areEqual(actualElement, expectedElement))
                //    throw failures.failure(info, elementsDifferAtIndex(actualElement, expectedElement, i, comparisonStrategy));
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



    public static List<Object> subtract(Object[] first, Object[] second) {
        List<Object> missingInFirst = new ArrayList<>();
        // use a copy to deal correctly with potential duplicates
        Object[] copyOfSecond = Arrays.copyOf(second, second.length);
        for (int i = 0; i < first.length; i++) {
            Object elementInFirst = first[i];
            if (MoreArrays.contains(copyOfSecond, elementInFirst)) {
                // remove the element otherwise a duplicate would be found in the case if there is one in actual
                remove(copyOfSecond, indexOf(copyOfSecond, elementInFirst));
            } else {
                missingInFirst.add(elementInFirst);
            }
        }
        return unmodifiableList(missingInFirst);
    }

    private static Object remove(final Object array, final int index) {
        final int length = getLength(array);
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        final Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        }

        return result;
    }

    /**
     * Returns the index of the first appearance of the value {@code target} in {@code array}.
     *
     * @param array an array of {@code byte} values, possibly empty
     * @param target a primitive {@code byte} value
     * @return the least index {@code i} for which {@code array[i] == target}, or {@code -1} if no
     *     such index exists.
     */
    public static int indexOf(Object[] array, Object target) {
        return indexOf(array, target, 0, array.length);
    }

    // TODO(kevinb): consider making this public
    private static int indexOf(Object[] array, Object target, int start, int end) {
        for (int i = start; i < end; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static class Difference {

        private List<Object> unexpected;
        private List<Object> missing;

        /**
         *
         * @param actual
         * @param expected
         * @return
         */
        public static Difference diff(Object[] actual, Object[] expected) {
            return new Difference(actual, expected);
        }

        /**
         *
         * @param actual
         * @param expected
         */
        public Difference(Object[] actual, Object[] expected) {
            this.unexpected = subtract(actual, expected);
            this.missing = subtract(expected, actual);
        }

        /**
         *
         * @return
         */
        public boolean differencesFound() {
            return hasUnexpected() || hasMissing();
        }

        /**
         *
         * @return  the elements in actual that are not in expected
         */
        public List<Object> getUnexpected() {
            return unexpected;
        }

        public boolean hasUnexpected() {
            return !unexpected.isEmpty();
        }

        /**
         *
         * @return  the elements in actual that are not in expected
         */
        public List<Object> getMissing() {
            return missing;
        }

        public boolean hasMissing() {
            return !missing.isEmpty();
        }
    }


    private List<?> convertToList(Object o) {
       Class<?> clazz = o.getClass().getComponentType();
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

        return null;
    }
}
