/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Forked from Hibernate Validator.
 */
public class MessageState implements ParserState {

    private static final Logger LOG = LoggerFactory.getLogger(MessageState.class);

    @Override
    public void terminate(TokenCollector tokenCollector) {
        tokenCollector.terminateToken();
    }

    @Override
    public void handleNonMetaCharacter(char character, TokenCollector tokenCollector) {
        tokenCollector.appendToToken(character);
    }

    @Override
    public void handleBeginTerm(char character, TokenCollector tokenCollector) {
        tokenCollector.terminateToken();

        // TODO: need to append even if PARAMETER as it sets the current token
        // if we dont we'll get NPE for PARAMETER
        // instead of adding a token and then marking token as parameter we could just
        // have a method to start a parameter token
        tokenCollector.appendToToken(character);
        if (tokenCollector.getInterpolationType().equals(InterpolationTermType.PARAMETER)) {
            tokenCollector.makeParameterToken();
        }

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
        tokenCollector.appendToToken(character);

        tokenCollector.transitionState(new EscapedState(this));
    }

    @Override
    public void handleELDesignator(char character, TokenCollector tokenCollector) {
        if (tokenCollector.getInterpolationType().equals(InterpolationTermType.PARAMETER)) {
            handleNonMetaCharacter(character, tokenCollector);
        } else {
            tokenCollector.transitionState(new ELState());
        }
    }
}
