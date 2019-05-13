package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// QUESTION: Should we remove this? I think we can do everything just via ArraySubject


/**
 * @param <S> the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *            Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T> the type of the instance.
 * @param <A> the type of the actual object being tested by this {@code Subject}. TODO: do we want to use A here given type is actually an array? Do we want to use E?
 *
 */
//* @param <ACTUAL> the type of the "actual" value which is an Array of ELEMENT.
//* @param <ELEMENT> the type of the "actual" array element.
public abstract class AbstractArraySubject<S extends AbstractArraySubject<S, T, A, E>, T, A, E>
    extends Subject<S, T, A>
    implements ArraySubject<S, E> {

    public AbstractArraySubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

}
