package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static jfluentvalidation.common.Uris.getParameters;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasNoParameterConstraint<T> extends AbstractConstraint<T, URL> {

    private final String name;
    private final String value;

    // TODO: I think we should remove this constructor and have a HasNoParameters constraint
    public HasNoParameterConstraint() {
        this(null, null);
    }

    public HasNoParameterConstraint(String name) {
        this(name, null);
    }

    public HasNoParameterConstraint(String name, String value) {
        super(DefaultMessages.HAS_NO_PARAMETER);
        this.name = name;
        this.value = value;  // unwantedValue
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (name == null) {
            return getParameters(context.getPropertyValue().getQuery()).isEmpty();
        }

        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
        boolean containsName = parameters.containsKey(name);
        if (value == null) {
            return !containsName;
        }

        List<String> values = parameters.get(name);
        return values == null || !values.contains(value);
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        if (name == null) {
//            return getParameters(context.getPropertyValue().getQuery()).isEmpty()
//                ? Empty.CONSTRAINT_VIOLATIONS
//                : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasNoParameter.message"));
//        }
//
//        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
//        boolean containsName = parameters.containsKey(name);
//
//        if (value == null) {
//            return containsName
//                ? Empty.CONSTRAINT_VIOLATIONS
//                : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasNoParameter.message"));
//        }
//
//        List<String> values = parameters.get(name);
//        return !values.contains(value)
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasNoParameter.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        if (name == null && !getParameters(context.getPropertyValue().getQuery()).isEmpty()) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//
//        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
//        if (value == null && !parameters.containsKey(name)) {
//            adddConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//    }

}
