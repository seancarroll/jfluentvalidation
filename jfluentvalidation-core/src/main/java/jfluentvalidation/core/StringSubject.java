package jfluentvalidation.core;

import jfluentvalidation.constraints.Constraint;

import java.util.function.Function;

public class StringSubject extends ComparableSubject<StringSubject, String> {

    public StringSubject(Function func) {
        super(StringSubject.class, "", func);
    }

    public StringSubject(String actual) {
        super(StringSubject.class, actual);
    }

    public StringSubject isLessThan(String value) {
        constraints.add(new LessThanConstraint(value));
        return myself;
    }

    public StringSubject isEmpty() {
        constraints.add(new IsEmptyConstraint());
        return myself;
    }

    public StringSubject isNotEmpty() {
        constraints.add(new IsNotEmptyConstraint());
        return myself;
    }

    public StringSubject startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    public StringSubject startsWith(String prefix, int offset) {
        constraints.add(new StartsWithConstraint(prefix));
        return myself;
    }

    public StringSubject minLength(int minLength) {
        constraints.add(new LengthConstraint(minLength, -1));
        return myself;
    }

    public StringSubject maxLength(int maxLength) {
        constraints.add((new LengthConstraint(0, maxLength)));
        return myself;
    }

    public StringSubject exactLength(int length) {
        constraints.add(new LengthConstraint(length, length));
        return myself;
    }

    public StringSubject length(int minLength, int maxLength) {
        constraints.add(new LengthConstraint(minLength, maxLength));
        return myself;
    }

    private class IsEmptyConstraint implements Constraint<String> {

        @Override
        public boolean isValid(String instance) {
            return instance.isEmpty();
        }
    }

    private class IsNotEmptyConstraint implements Constraint<String> {

        @Override
        public boolean isValid(String instance) {
            return !instance.isEmpty();
        }
    }

    private class LessThanConstraint implements Constraint<String> {

        private final String value;

        public LessThanConstraint(String value) {
            this.value = value;
        }

        @Override
        public boolean isValid(String instance) {
            return instance.compareTo(value) < 0;
        }
    }

    private class StartsWithConstraint implements Constraint<String> {

        private final String prefix;
        private final int offset;

        public StartsWithConstraint(String prefix) {
            this(prefix, 0);
        }

        public StartsWithConstraint(String prefix, int offset) {
            this.prefix = prefix;
            this.offset = offset;
        }

        @Override
        public boolean isValid(String instance) {
            // TODO: this probably should be based on a comparison strategy
            return instance.startsWith(prefix, offset);
        }
    }


    private class LengthConstraint implements Constraint<String> {

        private final int min;
        private final int max;

        public LengthConstraint(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean isValid(String instance) {

            int length = instance.length();
            if (length < min || (length > max && max != -1)) {
                return false;
            }

            return true;
        }
    }

}
