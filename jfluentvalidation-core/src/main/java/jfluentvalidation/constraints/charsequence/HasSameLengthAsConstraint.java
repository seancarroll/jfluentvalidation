package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation
// TODO: Do we need this as well as HasLengthConstraint?

/**
 *
 * @param <T>  type of instance to validate.
 */
public class HasSameLengthAsConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence other;

    public HasSameLengthAsConstraint(CharSequence other) {
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() == other.length();
    }
}
