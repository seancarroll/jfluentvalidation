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
public interface ParserState {

    void terminate(TokenCollector tokenCollector);

    void handleNonMetaCharacter(char character, TokenCollector tokenCollector);

    void handleBeginTerm(char character, TokenCollector tokenCollector);

    void handleEndTerm(char character, TokenCollector tokenCollector);

    void handleEscapeCharacter(char character, TokenCollector tokenCollector);

    void handleELDesignator(char character, TokenCollector tokenCollector);
}
