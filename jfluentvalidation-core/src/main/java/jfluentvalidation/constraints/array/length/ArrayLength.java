package jfluentvalidation.constraints.array.length;

// TODO: should we toss this into Arrays class?
// Part of me says to leave it here as it has a very specific use base and rule (max == -1) that
// really only applies to how we built the min/max array constraint logic
// In a real util/helper class I would just assert/ensure that min <= max
final class ArrayLength {

    private ArrayLength() {
        // statics only
    }


    static boolean min(int actual, int min) {
        return length(actual, min, -1);
    }

    // TODO: I think this should just take a shortcut and compare sizes rather than calling private length
    // There is a small perf hit when I benchmark it which makes sense as length does more work
    static boolean exact(int actual, int length) {
        return actual == length;
        //return length(actual, length, length);
    }

    static boolean max(int actual, int max) {
        return length(actual, 0, max);
    }

    private static boolean length(int actual, int min, int max) {
        if (actual < min || (actual > max && max != -1)) {
            return false;
        }

        return true;
    }

}
