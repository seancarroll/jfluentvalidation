package jfluentvalidation.core;


// assertJ has abstract class AbstractAssert and interface Assert
//* @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
//*          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
//*          for more details.
//* @param <ACTUAL> the type of the "actual" value.

// Following assertj style we could have an Constraint interface an an abstract class AbstractConstraint

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.object.IsEqualsConstraint;
import jfluentvalidation.constraints.object.IsNotNullConstraint;
import jfluentvalidation.constraints.object.IsNullConstraint;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

    protected Constraint<?> currentConstraint;

    public Subject(Class<?> selfType, Function<Object, A> propertyFunc, String propertyName) {

        this.myself = (S) selfType.cast(this);
        this.propertyFunc = propertyFunc;
        this.propertyName = propertyName;
    }

    /** Fails if the subject is not null. */
    // TODO: lets performance test the following
    // 1. constraints.add(instance -> instance == null)
    // 2. new IsNullConstraint
    // 3. static IsNullConstraint
    public S isNull() {
        // standardIsEqualTo(null);
        // constraints.add(instance -> instance == null);
        constraints.add(new IsNullConstraint());
        return myself;
    }

    /** Fails if the subject is null. */
    public S isNotNull() {
        // standardIsNotEqualTo(null);
        // constraints.add(instance -> instance != null);
        constraints.add(new IsNotNullConstraint());
        return myself;
    }

    // TODO: is there a better way to do this? What are some alternatives?
    public S isEquals(A other) {
        constraints.add(new IsEqualsConstraint<>(other));
        return myself;
    }

    public Function<Object, A> getPropertyFunc() {
        return propertyFunc;
    }

    public List<Constraint<?>> getConstraints() {
        return constraints;
    }

    protected void addConstraint(Constraint<?> constraint) {
        currentConstraint = constraint;
        constraints.add(constraint);
    }

    protected S withMessage(String message) {
        // TODO: implement
        // set message
        return myself;
    }

    protected S when(Predicate<A> predicate) {
        // TODO: implement
        return myself;
    }

    // TODO: enum instead of boolean applyToAll?
    protected S when(Predicate<A> predicate, boolean applyToAll) {
        // TODO: implement
        return myself;
    }

    protected S unless(Predicate<A> predicate) {
        // TODO: implement
        return myself;
    }

    // TODO: enum instead of boolean applyToAll?
    protected S unless(Predicate<A> predicate, boolean applyToAll) {
        // TODO: implement
        return myself;
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


//    when
//    unless
//    withMessage
//    withErrorCode
}
