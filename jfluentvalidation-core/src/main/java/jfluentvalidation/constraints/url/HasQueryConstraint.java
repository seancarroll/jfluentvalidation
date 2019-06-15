package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.Objects;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasQueryConstraint<T> extends AbstractConstraint<T, URL> {

    private final String expected;

    public HasQueryConstraint(String expected) {
        super(DefaultMessages.HAS_QUERY);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return Objects.equals(expected, context.getPropertyValue().getQuery());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }


//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        return context.getPropertyValue().getQuery().equals(expected)
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasQuery.message"));
//    }

//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (!expected.equals(context.getPropertyValue().getQuery())) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
