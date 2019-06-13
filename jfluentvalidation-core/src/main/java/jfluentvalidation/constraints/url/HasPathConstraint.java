package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.net.URL;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasPathConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasPathConstraint(@Nonnull String expected) {
        super(DefaultMessages.HAS_PATH);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return expected.equals(context.getPropertyValue().getPath());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return expected.equals(context.getPropertyValue().getPath())
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasPath.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (!expected.equals(context.getPropertyValue().getPath())) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

}
