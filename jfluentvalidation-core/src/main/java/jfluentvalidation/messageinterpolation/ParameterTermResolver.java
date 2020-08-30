/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import java.util.Arrays;
import java.util.Map;

/**
 * Forked from Hibernate Validator.
 */
public class ParameterTermResolver implements TermResolver {

    @Override
    public String interpolate(Map<String, Object> context, String expression) {
        Object variable = getVariable(context, removeCurlyBraces(expression));
        return variable == null ? expression : resolveExpression(variable);
    }

    private Object getVariable(Map<String, Object> context, String parameter) {
        return context.get(parameter);
    }

    private String removeCurlyBraces(String parameter) {
        return parameter.substring(1, parameter.length() - 1);
    }

    private String resolveExpression(Object variable) {
        final String resolvedExpression;
        if (variable.getClass().isArray()) {
            if (variable.getClass() == boolean[].class) {
                resolvedExpression = Arrays.toString((boolean[]) variable);
            } else if (variable.getClass() == char[].class) {
                resolvedExpression = Arrays.toString((char[]) variable);
            } else if (variable.getClass() == byte[].class) {
                resolvedExpression = Arrays.toString((byte[]) variable);
            } else if (variable.getClass() == short[].class) {
                resolvedExpression = Arrays.toString((short[]) variable);
            } else if (variable.getClass() == int[].class) {
                resolvedExpression = Arrays.toString((int[]) variable);
            } else if (variable.getClass() == long[].class) {
                resolvedExpression = Arrays.toString((long[]) variable);
            } else if (variable.getClass() == float[].class) {
                resolvedExpression = Arrays.toString((float[]) variable);
            } else if (variable.getClass() == double[].class) {
                resolvedExpression = Arrays.toString((double[]) variable);
            } else {
                resolvedExpression = Arrays.toString((Object[]) variable);
            }
        } else {
            resolvedExpression = variable.toString();
        }
        return resolvedExpression;
    }
}
