/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import java.util.Map;

/**
 * Forked from Hibernate Validator.
 */
public interface TermResolver {

    /**
     * Interpolates given term based on the constraint validation context.
     *
     * @param term the message to interpolate
     * @param context contextual information related to the interpolation
     *
     * @return interpolated message
     */
    String interpolate(Map<String, Object> context, String term);
}

