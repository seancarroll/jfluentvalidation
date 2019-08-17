package jfluentvalidation.constraints.iterable;

// TODO: singleton instead of statics?
/**
 *
 */
public final class IterableConstraints {

    private static final IsEmptyConstraint IS_EMPTY_CONSTRAINT = new IsEmptyConstraint();
    private static final IsNotEmptyConstraint IS_NOT_EMPTY_CONSTRAINT = new IsNotEmptyConstraint();

    public static <T> ContainsAllConstraint containsAllIn(Iterable<? super T> expectedIterable) {
        return new ContainsAllConstraint<>(expectedIterable);
    }

    public static <T> ContainsAnyConstraint containsAnyIn(Iterable<? super T> expectedIterable) {
        return new ContainsAnyConstraint<>(expectedIterable);
    }

    public static <T> ContainsConstraint contains(T element) {
        return new ContainsConstraint<>(element);
    }

    public static <T> ContainsExactlyConstraint containsExactlyElementsIn(Iterable<? super T> expectedIterable) {
        return new ContainsExactlyConstraint<>(expectedIterable);
    }

    public static <T> ContainsNoneConstraint containsNoneIn(Iterable<? super T> excluded) {
        return new ContainsNoneConstraint<>(excluded);
    }

    public static <T> DoesNotContainConstraint doesNotContain(T element) {
        return new DoesNotContainConstraint<>(element);
    }

    public static IsEmptyConstraint isEmpty() {
        return IS_EMPTY_CONSTRAINT;
    }

    public static IsNotEmptyConstraint isNotEmpty() {
        return IS_NOT_EMPTY_CONSTRAINT;
    }

    private IterableConstraints() {
        // public static factory methods only
    }
}
