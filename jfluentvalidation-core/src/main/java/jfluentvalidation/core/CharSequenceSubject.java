package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>
 * @param <A>
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
