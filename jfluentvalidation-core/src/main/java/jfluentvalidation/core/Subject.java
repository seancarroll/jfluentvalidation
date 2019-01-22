package jfluentvalidation.core;


// assertJ has abstract class AbstractAssert and interface Assert
//* @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
//*          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
//*          for more details.
//* @param <ACTUAL> the type of the "actual" value.

// Following assertj style we could have an Constraint interface an an abstract class AbstractConstraint

import jfluentvalidation.constraints.Constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * An object that lets you perform validation checks on the value under test.
 * @param <S> the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *           Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <A> the type of the actual object being tested by this {@code Subject}
 */
public class Subject<S extends Subject<S, A>, A> {

    protected final S myself;
    protected Function<Object, A> propertyFunc;
    protected String propertyName;
    protected List<Constraint<?>> constraints = new ArrayList<>();

    public Subject(Class<?> selfType, Function<Object, A> propertyFunc, String propertyName) {
        this.myself = (S) selfType.cast(this);
        this.propertyFunc = propertyFunc;
        this.propertyName = propertyName;
    }

    /** Fails if the subject is not null. */
    public S isNull() {
        // standardIsEqualTo(null);
        constraints.add(instance -> instance == null);
        return myself;
    }

    /** Fails if the subject is null. */
    public S isNotNull() {
        // standardIsNotEqualTo(null);
        constraints.add(instance -> instance != null);
        return myself;
    }

    public Function<Object, A> getPropertyFunc() {
        return propertyFunc;
    }

    public List<Constraint<?>> getConstraints() {
        return constraints;
    }




//    // truth called this standardIsEqualTo
//    private void isEqualTo(@NullableDecl Object expected) {
//        ComparisonResult difference = compareForEquality(expected);
//        if (!difference.valuesAreEqual()) {
//            failEqualityCheck(EqualityCheck.EQUAL, expected, difference);
//        }
//    }


    // isEqualTo
    // isNotEqualTo
    // isSameAs
    // isNotSameAs
    // isInstanceOf
    // isNotInstanceOf
    // isIn
    // isNotIn
    // isAnyOf
    // isNone
    // has
    // doesNotHave
    // is

}
