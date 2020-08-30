/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

/**
 * Forked from Hibernate Validator.
 */
public enum InterpolationTermType {
    /**
     * EL message expression, eg ${foo}.
     */
    EL,

    /**
     * Message parameter, eg {foo}.
     */
    PARAMETER
}
