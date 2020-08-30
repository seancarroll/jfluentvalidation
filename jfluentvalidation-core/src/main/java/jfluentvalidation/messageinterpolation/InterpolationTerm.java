/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import com.google.common.base.MoreObjects;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.Locale;
import java.util.Map;

/**
 * Forked from Hibernate Validator.
 */
public class InterpolationTerm {
    /**
     * Meta character to designate an EL expression.
     */
    private static final String EL_DESIGNATION_CHARACTER = "$";

    /**
     * The actual expression (parameter or EL expression).
     */
    private final String expression;

    /**
     * The type of the expression.
     */
    private final InterpolationTermType type;

    /**
     * The resolver for the expression.
     */
    private final TermResolver resolver;

    /**
     * Create an interpolation term for an expression.
     *
     * @param expression        the expression.
     * @param locale            the locale.
     * @param factory the expression factory to use if the expression uses EL.
     */
    public InterpolationTerm(String expression, Locale locale, MapVariableResolverFactory factory) {
        this.expression = expression;
        if (isElExpression(expression)) {
            this.type = InterpolationTermType.EL;
            this.resolver = new ElTermResolver(locale, factory);
        } else {
            this.type = InterpolationTermType.PARAMETER;
            this.resolver = new ParameterTermResolver();
        }
    }

    public static boolean isElExpression(String expression) {
        return expression.startsWith(EL_DESIGNATION_CHARACTER);
    }

    public String interpolate(Map<String, Object> context) {
        return resolver.interpolate(context, expression);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("expression", expression)
            .add("type", type)
            .toString();
    }
}

