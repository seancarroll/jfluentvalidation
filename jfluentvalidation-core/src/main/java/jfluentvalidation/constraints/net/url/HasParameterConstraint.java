package jfluentvalidation.constraints.net.url;

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
public class HasParameterConstraint<T> extends AbstractConstraint<T, URL> {

    // expectedParameterName
    private final String name;

    // expectedParameterValue
    private final String value;

    public HasParameterConstraint(String name) {
        this(name, null);
    }

    public HasParameterConstraint(String name, String value) {
        super(DefaultMessages.NET_HAS_PARAMETER);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
        boolean containsName = parameters.containsKey(name);

        if (value == null) {
            return containsName;
        }
        List<String> values = parameters.get(name);
        return values.contains(value);
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }

//    @Override
//    public List<ConstraintViolation> isValid(RuleContext<T, URL> context) {
//        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
//        boolean containsName = parameters.containsKey(name);
//
//        // TODO: do we need two separate messages? one with just the name for here and one with both name and value for below?
//        if (value == null) {
//             return containsName
//                 ? Empty.CONSTRAINT_VIOLATIONS
//                 : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasParameter.message"));
//        }
//
//        List<String> values = parameters.get(name);
//        return values.contains(value)
//            ? Empty.CONSTRAINT_VIOLATIONS
//            : Collections.singletonList(ConstraintViolation.create(context,"jfluentvalidation.constraints.HasParameter.message"));
//    }
//
//    @Override
//    protected void validate(RuleContext<T, URL> context) {
//        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
//        boolean containsName = parameters.containsKey(name);
//
//        if (value == null) {
//            if (!containsName) {
//                addConstraint(ConstraintViolation.create(context, MESSAGE));
//            }
//            return;
//        }
//
//        // TODO: could return null
//        List<String> values = parameters.get(name);
//        if (!values.contains(value)) {
//            addConstraint(ConstraintViolation.create(context, MESSAGE));
//        }
//
//    }

    @Override
    public void addParametersToContext(RuleContext<T, URL> context) {
        context.getMessageFormatter().appendArgument("ParameterName", name);
        context.getMessageFormatter().appendArgument("ParameterValue", value);
        context.getValidationContext().getContextData().put("ParameterName", name);
        context.getValidationContext().getContextData().put("ParameterValue", value);
    }

}
