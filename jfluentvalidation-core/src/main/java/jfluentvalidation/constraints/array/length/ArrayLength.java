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
        return between(actual, min, -1);
    }

    static boolean max(int actual, int max) {
        return between(actual, 0, max);
    }

    static boolean between(int actual, int min, int max) {
        if (actual < min || (actual > max && max != -1)) {
            return false;
        }

        return true;
    }

    static boolean between(int actual, int min, int max, boolean includeMin, boolean includeMax) {
        if (actual < min || (actual > max && max != -1)) {
            return false;
        }

        return true;
    }


}
