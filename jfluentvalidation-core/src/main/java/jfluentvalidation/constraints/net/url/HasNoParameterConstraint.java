package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

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
        super(DefaultMessages.NET_HAS_NO_PARAMETER);
        this.name = name;
        this.value = value;  // unwantedValue
    }

    @Override
    public boolean isValid(ConstraintContext<T, URL> context) {
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
    public void addParametersToContext(ConstraintContext<T, URL> context) {
        context.getMessageContext().appendArgument("ParameterName", name);
        context.getMessageContext().appendArgument("ParameterValue", value);
    }
}
