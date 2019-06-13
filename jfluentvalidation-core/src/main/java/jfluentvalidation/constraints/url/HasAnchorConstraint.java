package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.Objects;

/**
 * Gets the anchor (also known as the "reference") of this {@code URL}.
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasAnchorConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasAnchorConstraint(String expected) {
        super(DefaultMessages.URL_HAS_ANCHOR);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return Objects.equals(expected, context.getPropertyValue().getRef());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return context.getPropertyValue().getRef().equals(expected)
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasAnchor.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (!expected.equals(context.getPropertyValue().getRef())) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

}
