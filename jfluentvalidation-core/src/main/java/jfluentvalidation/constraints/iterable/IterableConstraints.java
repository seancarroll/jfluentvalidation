package jfluentvalidation.constraints.iterable;

/**
 *
 */
public final class IterableConstraints {

    private static final IsEmptyConstraint IS_EMPTY_CONSTRAINT = new IsEmptyConstraint();
    private static final IsNotEmptyConstraint IS_NOT_EMPTY_CONSTRAINT = new IsNotEmptyConstraint();

    public static <T> ContainsAllInConstraint containsAllIn(Iterable<? super T> expectedIterable) {
        return new ContainsAllInConstraint<>(expectedIterable);
    }

    public static <T> ContainsAnyInConstraint containsAnyIn(Iterable<? super T> expectedIterable) {
        return new ContainsAnyInConstraint<>(expectedIterable);
    }

    public static <T> ContainsConstraint contains(T element) {
        return new ContainsConstraint<>(element);
    }

    public static <T> ContainsExactlyElementsInConstraint containsExactlyElementsIn(Iterable<? super T> expectedIterable) {
        return new ContainsExactlyElementsInConstraint<>(expectedIterable);
    }

    public static <T> ContainsNoneInConstraint containsNoneIn(Iterable<? super T> excluded) {
        return new ContainsNoneInConstraint<>(excluded);
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
