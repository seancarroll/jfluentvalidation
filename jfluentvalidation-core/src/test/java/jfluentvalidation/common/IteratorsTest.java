package jfluentvalidation.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static jfluentvalidation.TestUtils.createIterable;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: tests can just go through Iterables instead of Iterators
class IteratorsTest {

    @Test
    void containsNull() {
        Iterable<String> iterable = createIterable("hello", null);
        assertTrue(Iterables.contains(iterable, null));
    }

    @Test
    void contains() {
        List<String> strings = Arrays.asList("hello", null);
        assertTrue(Iterables.contains(strings, null));
    }

}
