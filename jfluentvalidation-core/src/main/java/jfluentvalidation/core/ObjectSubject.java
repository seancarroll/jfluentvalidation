package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@link Object} subjects.
 *
 * @param <T>  the type of the instance
 */
public class ObjectSubject<T> extends Subject<ObjectSubject<T>, T, Object> {

    public ObjectSubject(PropertyRule<T, Object> rule) {
        super(ObjectSubject.class, rule);
    }
}
