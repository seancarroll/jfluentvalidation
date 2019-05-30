package jfluentvalidation.common;

import static jfluentvalidation.common.Comparables.isGreaterThan;
import static jfluentvalidation.common.Comparables.isLessThan;

// TODO: not sure if this is the appropriate place for this but good enough for now
public final class Ints {

    private Ints() {
        // statics only
    }

    public static boolean between(int actual, int start, int end, boolean inclusiveStart, boolean inclusiveEnd) {
        boolean checkLowerBoundaryRange = inclusiveStart ? !isGreaterThan(start, actual) : isLessThan(start, actual);
        boolean checkUpperBoundaryRange = inclusiveEnd ? !isGreaterThan(actual, end) : isLessThan(actual, end);

        return checkLowerBoundaryRange && checkUpperBoundaryRange;
    }

}
