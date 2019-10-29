package jfluentvalidation.constraints.object;

// TODO: singleton instead of statics?
/**
 * Static factory
 */
public final class ObjectConstraints {

    // TODO: do we need to make these use a generic object?
    private static final IsNotNullConstraint IS_NOT_NULL_CONSTRAINT = new IsNotNullConstraint();
    private static final IsNullConstraint IS_NULL_CONSTRAINT = new IsNullConstraint();

    public static <T> IsEqualToConstraint isEqualTo(T other) {
        return new IsEqualToConstraint<>(other);
    }

    public static <T> IsNotEqualToConstraint isNotEqualTo(T other) {
        return new IsNotEqualToConstraint<>(other);
    }

    public static IsNotNullConstraint isNotNull() {
        return IS_NOT_NULL_CONSTRAINT;
    }

    public static IsNullConstraint isNull() {
        return IS_NULL_CONSTRAINT;
    }

    private ObjectConstraints() {
        // public static factory methods only
    }
}
