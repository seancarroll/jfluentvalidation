package jfluentvalidation.constraints.iterable;

// TODO: generics
public final class IterableConstraints {

    public static <T> ContainsAllInConstraint containsAllIn(Iterable<? super T> expectedIterable) {
        return new ContainsAllInConstraint(expectedIterable);
    }

    public static <T> ContainsAnyInConstraint containsAnyIn(Iterable<? super T> expectedIterable) {
        return new ContainsAnyInConstraint(expectedIterable);
    }

    public static <T> ContainsConstraint contains(T element) {
        return new ContainsConstraint(element);
    }

    public static <T> ContainsExactlyElementsInConstraint containsExactlyElementsIn(Iterable<? super T> expectedIterable) {
        return new ContainsExactlyElementsInConstraint(expectedIterable);
    }

    public static <T> ContainsNoneInConstraint containsNoneIn(Iterable<? super T> excluded) {
        return new ContainsNoneInConstraint(excluded);
    }

    public static <T> DoesNotContainConstraint doesNotContain(T element) {
        return new DoesNotContainConstraint(element);
    }

    public static IsEmptyConstraint isEmpty() {
        // TODO: use a final static version of this
        return new IsEmptyConstraint();
    }

    public static IsNotEmptyConstraint isNotEmpty() {
        // TODO: use a final static version of this
        return new IsNotEmptyConstraint();
    }

    private IterableConstraints() {
        // public static factory methods only
    }
}
