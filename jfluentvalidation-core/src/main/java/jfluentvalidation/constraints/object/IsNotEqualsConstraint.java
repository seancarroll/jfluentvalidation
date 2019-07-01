package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Objects;

/**
 * Check that the given {@code Object} being validated is not equal to the given object.
 *
 * @param <T>  the target type supported by an implementation.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotEqualsConstraint<T, P> extends AbstractConstraint<T, P> {

    private final P other;

    public IsNotEqualsConstraint(P other) {
        super(DefaultMessages.IS_NOT_EQUALS);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return !Objects.equals(context.getPropertyValue(), other);
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    protected void validate(RuleContext<T, P> context) {
//        if (Objects.equals(context.getPropertyValue(), other)) {
//            addConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
