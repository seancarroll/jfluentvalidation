package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static jfluentvalidation.common.Uris.getParameters;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasParameterConstraint<T> implements Constraint<T, URL> {

    // expectedParameterName
    private final String name;

    // expectedParameterValue
    private final String value;

    public HasParameterConstraint(String name) {
        this(name, null);
    }

    public HasParameterConstraint(String name, String value) {
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

}
