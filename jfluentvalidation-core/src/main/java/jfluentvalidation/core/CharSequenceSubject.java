package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>
 */
public class CharSequenceSubject<T> extends AbstractCharSequenceSubject<CharSequenceSubject<T>, T, CharSequence> {

    public CharSequenceSubject(PropertyRule<T, CharSequence> rule) {
        super(CharSequenceSubject.class, rule);
    }

}
