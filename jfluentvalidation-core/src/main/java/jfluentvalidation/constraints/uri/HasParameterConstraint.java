package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static jfluentvalidation.common.Uris.getParameters;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasParameterConstraint<T> extends AbstractConstraint<T, URI> {

    // expectedParameterName
    private final String name;

    // expectedParameterValue
    private final String value;

    public HasParameterConstraint(String name) {
        this(name, null);
    }

    public HasParameterConstraint(String name, String value) {
        super(DefaultMessages.HAS_PARAMETER);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
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


//
//    @Override
//    protected void validate(RuleContext<T, URI> context) {
//        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
//        boolean containsName = parameters.containsKey(name);
//
//        if (value == null) {
//            if (!containsName) {
//                adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//            }
//            return;
//        }
//
//        // TODO: could return null
//        List<String> values = parameters.get(name);
//        if (!values.contains(value)) {
//            adddConstraint(ConstraintViolation.create(context, DEFAULT_MESSAGE));
//        }
//    }

}
