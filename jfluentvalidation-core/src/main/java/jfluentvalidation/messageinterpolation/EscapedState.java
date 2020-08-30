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
public class EscapedState implements ParserState {
    private final ParserState previousState;

    public EscapedState(ParserState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void terminate(TokenCollector tokenCollector) {
        tokenCollector.terminateToken();
    }

    @Override
    public void handleNonMetaCharacter(char character, TokenCollector tokenCollector) {
        handleEscapedCharacter(character, tokenCollector);
    }

    @Override
    public void handleBeginTerm(char character, TokenCollector tokenCollector) {
        handleEscapedCharacter(character, tokenCollector);
    }

    @Override
    public void handleEndTerm(char character, TokenCollector tokenCollector) {
        handleEscapedCharacter(character, tokenCollector);
    }

    @Override
    public void handleEscapeCharacter(char character, TokenCollector tokenCollector) {
        handleEscapedCharacter(character, tokenCollector);
    }

    @Override
    public void handleELDesignator(char character, TokenCollector tokenCollector) {
        handleEscapedCharacter(character, tokenCollector);
    }

    private void handleEscapedCharacter(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(character);
        tokenCollector.transitionState(previousState);
    }
}
