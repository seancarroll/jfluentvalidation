package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@code boolean} subjects.
 *
 * @param <T>  the type of the instance
 */
public class BooleanSubject<T> extends AbstractComparableSubject<BooleanSubject<T>, T, Boolean> {

    public BooleanSubject(PropertyRule<T, Boolean> rule) {
        super(BooleanSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public BooleanSubject<T> isTrue() {
        return isEqualTo(true);
    }

    @CanIgnoreReturnValue
    public BooleanSubject<T> isFalse() {
        return isEqualTo(false);
    }

}
