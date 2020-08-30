/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jfluentvalidation.messageinterpolation.InterpolationHelper.BEGIN_TERM;

/**
 * Forked from Hibernate Validator.
 */
public class InterpolationTermState implements ParserState {

    private final Logger LOG = LoggerFactory.getLogger(InterpolationTermState.class);

    @Override
    public void terminate(TokenCollector tokenCollector) {
        throw new InvalidMessageException(
            tokenCollector.getOriginalMessageDescriptor(),
            BEGIN_TERM
        );

    }

    @Override
    public void handleNonMetaCharacter(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(character);
    }

    @Override
    public void handleBeginTerm(char character, TokenCollector tokenCollector) {
        throw new InvalidMessageException(tokenCollector.getOriginalMessageDescriptor());
    }

    @Override
    public void handleEndTerm(char character, TokenCollector tokenCollector) {
        // TODO: dont append token
        // this is a '}' and we have to remove it anyway to lookup the value anyway so no need to append it
        // issue is that appendToToken sets currentToken and if we dont append we'll get NPE
        tokenCollector.appendToToken(character);
        tokenCollector.terminateToken();
        tokenCollector.transitionState(new MessageState());
    }

    @Override
    public void handleEscapeCharacter(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(character);
        ParserState state = new EscapedState(this);
        tokenCollector.transitionState(state);
    }

    @Override
    public void handleELDesignator(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(character);
    }
}
