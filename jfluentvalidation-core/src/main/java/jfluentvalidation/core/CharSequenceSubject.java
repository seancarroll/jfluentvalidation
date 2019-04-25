package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>
 */
public class CharSequenceSubject<T, A extends CharSequence> extends AbstractCharSequenceSubject<CharSequenceSubject<T, A>, T, A> {

    public CharSequenceSubject(PropertyRule<T, A> rule) {
        super(CharSequenceSubject.class, rule);
    }
}
