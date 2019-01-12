package jfluentvalidation.core;

import jfluentvalidation.constraints.Constraint;

import java.util.function.Function;

public class StringSubject extends ComparableSubject<StringSubject, String> {

    private Function<Object, String> func;

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

    private class IsEmptyConstraint implements Constraint<String> {

        @Override
        public boolean IsValid(String instance) {
            return instance.isEmpty();
        }
    }

    private class IsNotEmptyConstraint implements Constraint<String> {

        @Override
        public boolean IsValid(String instance) {
            return !instance.isEmpty();
        }
    }


    private class LessThanConstraint implements Constraint<String> {

        private final String value;

        public LessThanConstraint(String value) {
            this.value = value;
        }

        @Override
        public boolean IsValid(String instance) {
            return instance.compareTo(value) < 0;
        }
    }

}
