package jfluentvalidation.common;

import org.junit.jupiter.api.Test;

import java.util.List;

class MoreCollectionsTest {

    // TODO: add tests

    @Test
    void accumulate() {
        List<String> accumulated = MoreCollections.accumulate("first", "second");
        List<String> accumulated2 = MoreCollections.accumulate("first", "second", null);
        List<String> accumulated3 = MoreCollections.accumulate("first", "second", "third", "fourth");
    }
}
