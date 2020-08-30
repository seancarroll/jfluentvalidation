/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import org.mvel2.MVEL;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.Locale;
import java.util.Map;

/**
 * Forked from Hibernate Validator.
 */
public class ElTermResolver implements TermResolver {

    /**
     * Name under which the currently validate value is bound to the EL context.
     */
    private static final String VALIDATED_VALUE_NAME = "validatedValue";

    /**
     * The locale for which to interpolate the expression.
     */
    private final Locale locale;

    /**
     * Factory for creating EL expressions
     */
    private final MapVariableResolverFactory factory;

    /**
     * Construct the resolver. The expression factory has to be passed in to ensure that it is
     * set up early and to allow for application control.
     * @param locale the locale.
     * @param factory the expression factory.
     */
    public ElTermResolver(Locale locale, MapVariableResolverFactory factory) {
        this.locale = locale;
        this.factory = factory;
    }

    @Override
    public String interpolate(Map<String, Object> context, String expression) {
        return MVEL.evalToString(removeDollarAndCurlyBraces(expression), factory);
    }

    private static String removeDollarAndCurlyBraces(String parameter) {
        return parameter.substring(2, parameter.length() - 1);
    }
}
