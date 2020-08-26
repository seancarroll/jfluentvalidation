package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

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
        super(DefaultMessages.NET_HAS_PARAMETER);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean isValid(ConstraintContext<T, URI> context) {
        Map<String, List<String>> parameters = getParameters(context.getPropertyValue().getQuery());
        boolean containsName = parameters.containsKey(name);

        if (value == null) {
            return containsName;
        }

        List<String> values = parameters.get(name);
        return values.contains(value);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, URI> context) {
        context.getMessageContext().appendArgument("ParameterName", name);
        context.getMessageContext().appendArgument("ParameterValue", value);
    }
}
