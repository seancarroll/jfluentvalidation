package jfluentvalidation.core;

import jfluentvalidation.constraints.strings.*;

import java.util.function.Function;

public class StringSubject extends ComparableSubject<StringSubject, String> {

    public StringSubject(Function func, String propertyName) {
        super(StringSubject.class, func, propertyName);
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

    public StringSubject startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    public StringSubject startsWith(String prefix, int offset) {
        constraints.add(new StartsWithConstraint(prefix));
        return myself;
    }



}
