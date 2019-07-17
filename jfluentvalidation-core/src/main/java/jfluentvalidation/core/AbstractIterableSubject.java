package jfluentvalidation.core;

//* @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
//*          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
//*          for more details.
//* @param <A> the type of the "actual" value.
//* @param <E> the type of elements of the "actual" value.
//* @param <I> element assert, used for navigational assertions to return the right assert type.
//
//public abstract class AbstractIterableAssert<SELF extends AbstractIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
//    ACTUAL extends Iterable<? extends ELEMENT>,
//    ELEMENT,
//    ELEMENT_ASSERT extends AbstractAssert<ELEMENT_ASSERT, ELEMENT>>
//    extends AbstractAssert<SELF, ACTUAL>
//    implements ObjectEnumerableAssert<SELF, ELEMENT>

import jfluentvalidation.rules.PropertyRule;


//public class AbstractIterableSubject<S extends AbstractIterableSubject<S, T, A, E, I>,
//    T,
//    A extends Iterable<? extends E>,
//    E,
//    I extends Subject<I, T, E>>
//    extends Subject<S, T, A>
//// implements ObjectEnumerableAssert<SELF, ELEMENT>{

public class AbstractIterableSubject<S extends AbstractIterableSubject<S, T, A, E>,
                                     T,
                                     A extends Iterable<? super E>,
                                     E>
    extends Subject<S, T, A> {

    public AbstractIterableSubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }
}
