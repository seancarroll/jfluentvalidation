package jfluentvalidation.core;


//* Base class for all assertions.
//*
//* @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
//*          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
//*          for more details.
//* @param <ACTUAL> the type of the "actual" value.

// assertJ has abstract class AbstractAssert and interface Assert
//* @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
//*          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
//*          for more details.
//* @param <ACTUAL> the type of the "actual" value.

// Following assertj style we could have an Constraint interface an an abstract class AbstractConstraint

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.PredicateConstraint;
import jfluentvalidation.constraints.object.IsEqualToConstraint;
import jfluentvalidation.constraints.object.IsNotEqualToConstraint;
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

    // TODO: do we need this? we added currentConstraint to rule...is that were it should be?
    // protected Constraint<T, A> currentConstraint;

    public Subject(Class<?> selfType, PropertyRule<T, A> rule) {
        this.myself = (S) selfType.cast(this);
        this.rule = rule;
    }

    /** Fails if the subject is not null. */
    // TODO: lets performance test the following
    // 1. constraints.add(instance -> instance == null)
    // 2. new IsNullConstraint
    // 3. static IsNullConstraint
    @CanIgnoreReturnValue
    public void isNull() {
        // standardIsEqualTo(null);
        rule.addConstraint(new IsNullConstraint<>());
    }

    /** Fails if the subject is null. */
    @CanIgnoreReturnValue
    public S isNotNull() {
        // standardIsNotEqualTo(null);
        rule.addConstraint(new IsNotNullConstraint<>());
        return myself;
    }

    // TODO: is there a better way to do this? What are some alternatives?
    @CanIgnoreReturnValue
    public S isEqualTo(A other) {
        rule.addConstraint(new IsEqualToConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public S isNotEqualTo(A other) {
        rule.addConstraint(new IsNotEqualToConstraint<>(other));
        return myself;
    }

    public S withMessage(String message) {
        rule.getCurrentConstraint().getOptions().setErrorMessage(message);
        return myself;
    }

    public S when(Predicate<T> predicate) {
        return when(predicate, true);
    }

    // TODO: enum instead of boolean applyToAll?
    public S when(Predicate<T> predicate, boolean applyToAll) {
        // TODO: use applyToAll
        // TODO: crap...SoftConstraint predicate could be on a different type than the constraint
        rule.applyCondition(predicate, applyToAll);
        return myself;
    }

    public S unless(Predicate<T> predicate) {
        return unless(predicate, true);
    }

    // TODO: enum instead of boolean applyToAll?
    public S unless(Predicate<T> predicate, boolean applyToAll) {
        rule.applyCondition(predicate.negate(), applyToAll);
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
