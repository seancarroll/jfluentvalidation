package jfluentvalidation.constraints.object;

/**
 *
 */
public final class ObjectConstraints {

    // TODO: do we need to make these use a generic object?
    private static final IsNotNullConstraint IS_NOT_NULL_CONSTRAINT = new IsNotNullConstraint();
    private static final IsNullConstraint IS_NULL_CONSTRAINT = new IsNullConstraint();

    public static final <T> IsEqualsConstraint isEquals(T other) {
        return new IsEqualsConstraint(other);
    }

    public static final <T> IsNotEqualsConstraint isNotEquals(T other) {
        return new IsNotEqualsConstraint(other);
    }

    public static final IsNotNullConstraint isNotNull() {
        return IS_NOT_NULL_CONSTRAINT;
    }

    public static final IsNullConstraint isNull() {
        return IS_NULL_CONSTRAINT;
    }

    private ObjectConstraints() {
        // public static factory methods only
    }
}
