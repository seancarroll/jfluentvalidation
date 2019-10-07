package jfluentvalidation.constraints.net.uri;

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
public class HasNoParameterConstraint<T> extends AbstractConstraint<T, URI> {

    private final String name;
    private final String value;

    public HasNoParameterConstraint() {
        this(null, null);
    }

    public HasNoParameterConstraint(String name) {
        this(name, null);
    }

    public HasNoParameterConstraint(String name, String value) {
        super(DefaultMessages.NET_HAS_NO_PARAMETER);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
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

    @Override
    public void addParametersToContext(RuleContext<T, URI> context) {
        context.getMessageContext().appendArgument("ParameterName", name);
        context.getMessageContext().appendArgument("ParameterValue", value);
    }
}
