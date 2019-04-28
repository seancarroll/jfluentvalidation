package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public class CharSequenceSubject<T, A extends CharSequence> extends AbstractCharSequenceSubject<CharSequenceSubject<T, A>, T, A> {

    /**
     * 
     * @param rule
     */
    public CharSequenceSubject(PropertyRule<T, A> rule) {
        super(CharSequenceSubject.class, rule);
    }
}
