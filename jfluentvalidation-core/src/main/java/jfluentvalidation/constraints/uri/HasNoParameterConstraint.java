package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static jfluentvalidation.common.Uris.getParameters;

public class HasNoParameterConstraint<T> implements Constraint<T, URI> {

    private final String name;
    private final String value;

    public HasNoParameterConstraint() {
        this(null, null);
    }

    public HasNoParameterConstraint(String name) {
        this(name, null);
    }

    public HasNoParameterConstraint(String name, String value) {
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
            return containsName;
        }

        List<String> values = parameters.get(name);
        return !values.contains(value);
    }
}