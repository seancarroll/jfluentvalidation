/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package jfluentvalidation.messageinterpolation;

import static jfluentvalidation.messageinterpolation.InterpolationHelper.EL_DESIGNATOR;

/**
 * Forked from Hibernate Validator.
 */
public class ELState implements ParserState {

    @Override
    public void terminate(TokenCollector tokenCollector) {
        tokenCollector.appendToToken(EL_DESIGNATOR);
        tokenCollector.terminateToken();
    }

    @Override
    public void handleNonMetaCharacter(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(EL_DESIGNATOR);
        tokenCollector.appendToToken(character);
        tokenCollector.terminateToken();
        tokenCollector.transitionState(new MessageState());
    }

    @Override
    public void handleBeginTerm(char character, TokenCollector tokenCollector) {
        tokenCollector.terminateToken();

        // TODO: dont append either EL_DESIGNATOR or character (which is '{')
        // as we have to remove them anyway to resolve the EL expression
//        tokenCollector.appendToToken(EL_DESIGNATOR);
//        tokenCollector.appendToToken(character);
        tokenCollector.makeELToken();
        tokenCollector.transitionState(new InterpolationTermState());
    }

    @Override
    public void handleEndTerm(char character, TokenCollector tokenCollector) {
        throw new InvalidMessageException(
            tokenCollector.getOriginalMessageDescriptor(),
            character
        );
    }

    @Override
    public void handleEscapeCharacter(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(EL_DESIGNATOR);
        tokenCollector.appendToToken(character);
        // Do not go back to this state after the escape: $\ is not the start of an EL expression
        ParserState stateAfterEscape = new MessageState();
        tokenCollector.transitionState(new EscapedState(stateAfterEscape));
    }

    @Override
    public void handleELDesignator(char character, TokenCollector tokenCollector) {
        handleNonMetaCharacter(character, tokenCollector);
    }
}
