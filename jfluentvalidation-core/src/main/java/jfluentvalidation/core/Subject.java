package jfluentvalidation.core;


// assertJ has abstract class AbstractAssert and interface Assert
//* @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
//*          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
//*          for more details.
//* @param <ACTUAL> the type of the "actual" value.

// Following assertj style we could have an Constraint interface an an abstract class AbstractConstraint

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.PredicateConstraint;
import jfluentvalidation.constraints.object.IsEqualsConstraint;
import jfluentvalidation.constraints.object.IsNotEqualsConstraint;
import jfluentvalidation.constraints.object.IsNotNullConstraint;
import jfluentvalidation.constraints.object.IsNullConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.function.Predicate;

/**
 * An object that lets you perform validation checks on the value under test.
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance
 * @param <A>  the type of the actual object being tested by this {@code Subject}
 */
public class Subject<S extends Subject<S, T, A>, T, A> {

    // TODO: does the subject need propertyFunc, propertyName, constraints, currentConstraint or can these be moved elsewhere?
    protected final S myself;
    protected PropertyRule<T, A> rule;
    protected Constraint<?, A> currentConstraint;

    public Subject(Class<?> selfType, PropertyRule<T, A> rule) {
        this.myself = (S) selfType.cast(this);
        this.rule = rule;
    }

    /** Fails if the subject is not null. */
    // TODO: lets performance test the following
    // 1. constraints.add(instance -> instance == null)
    // 2. new IsNullConstraint
    // 3. static IsNullConstraint
    public S isNull() {
        // standardIsEqualTo(null);
        rule.addConstraint(new IsNullConstraint<>());
        return myself;
    }

    /** Fails if the subject is null. */
    public S isNotNull() {
        // standardIsNotEqualTo(null);
        rule.addConstraint(new IsNotNullConstraint<>());
        return myself;
    }

    // TODO: is there a better way to do this? What are some alternatives?
    public S isEquals(A other) {
        rule.addConstraint(new IsEqualsConstraint<>(other));
        return myself;
    }

    public S isNotEquals(A other) {
        rule.addConstraint(new IsNotEqualsConstraint<>(other));
        return myself;
    }

    public S withMessage(String message) {
        rule.getCurrentConstraint().getOptions().setErrorMessage(message);
        return myself;
    }

    public S when(Predicate<A> predicate) {
        return when(predicate, true);
    }

    // TODO: enum instead of boolean applyToAll?
    public S when(Predicate<A> predicate, boolean applyToAll) {
        // TODO: implement
        return myself;
    }

    public S unless(Predicate<A> predicate) {
        return unless(predicate, true);
    }

    // TODO: enum instead of boolean applyToAll?
    public S unless(Predicate<A> predicate, boolean applyToAll) {
        // TODO: implement
        return myself;
    }


    //    RuleFor(x => x.Postcode).Must(BeAValidPostcode).WithMessage("Please specify a valid postcode");
    public S must(Predicate<A> predicate) {
        rule.addConstraint(new PredicateConstraint<>(predicate));
        return myself;
    }

    protected PropertyRule<T, A> getRule() {
        return rule;
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

    // TODO: should this have ruleSet?

//    when
//    unless
//    withMessage
//    withErrorCode
}
