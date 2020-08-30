/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package jfluentvalidation.messageinterpolation;

import java.util.Collections;
import java.util.List;

import static jfluentvalidation.common.Lists.newArrayList;
import static jfluentvalidation.messageinterpolation.InterpolationHelper.BEGIN_TERM;
import static jfluentvalidation.messageinterpolation.InterpolationHelper.EL_DESIGNATOR;
import static jfluentvalidation.messageinterpolation.InterpolationHelper.END_TERM;
import static jfluentvalidation.messageinterpolation.InterpolationHelper.ESCAPE_CHARACTER;

/**
 * Forked from Hibernate Validator.
 */
public class TokenCollector {

    private final String originalMessageDescriptor;
    private final InterpolationTermType interpolationTermType;

    private final List<Token> tokenList;
    private ParserState currentParserState;
    private int currentPosition;
    private Token currentToken;

    public TokenCollector(String originalMessageDescriptor, InterpolationTermType interpolationTermType) {
        this.originalMessageDescriptor = originalMessageDescriptor;
        this.interpolationTermType = interpolationTermType;
        this.currentParserState = new MessageState();
        this.tokenList = newArrayList();

        parse();
    }

    public void terminateToken() {
        if (currentToken == null) {
            return;
        }
        currentToken.terminate();
        tokenList.add(currentToken);
        currentToken = null;
    }

    public void appendToToken(char character) {
        if (currentToken == null) {
            currentToken = new Token(character);
        } else {
            currentToken.append(character);
        }
    }

    public void makeParameterToken() {
        currentToken.makeParameterToken();
    }

    public void makeELToken() {
        currentToken.makeELToken();
    }

    private void next() {
        if (currentPosition == originalMessageDescriptor.length()) {
            // give the current context the chance to complete
            currentParserState.terminate(this);
            currentPosition++;
            return;
        }
        char currentCharacter = originalMessageDescriptor.charAt(currentPosition);
        currentPosition++;
        switch (currentCharacter) {
            case BEGIN_TERM: {
                currentParserState.handleBeginTerm(currentCharacter, this);
                break;
            }
            case END_TERM: {
                currentParserState.handleEndTerm(currentCharacter, this);
                break;
            }
            case EL_DESIGNATOR: {
                currentParserState.handleELDesignator(currentCharacter, this);
                break;
            }
            case ESCAPE_CHARACTER: {
                currentParserState.handleEscapeCharacter(currentCharacter, this);
                break;
            }
            default: {
                currentParserState.handleNonMetaCharacter(currentCharacter, this);
            }
        }
    }

    public final void parse() {
        while (currentPosition <= originalMessageDescriptor.length()) {
            next();
        }
    }

    public void transitionState(ParserState newState) {
        currentParserState = newState;
    }

    public InterpolationTermType getInterpolationType() {
        return interpolationTermType;
    }

    public List<Token> getTokenList() {
        return Collections.unmodifiableList(tokenList);
    }

    public String getOriginalMessageDescriptor() {
        return originalMessageDescriptor;
    }
}
