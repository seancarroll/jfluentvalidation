package jfluentvalidation.messageinterpolation;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

public class TokenizedMessage {
    // TODO: replace this with something similar to hibernate validator...assuming its quicker but need to verify
    private static final Pattern TOKEN_PATTERN = Pattern.compile("([^${]|(?<=\\\\)[${])+|\\$?\\{[^${]*}");

    private final List<Token> tokens;
    private boolean hasEL;

    public TokenizedMessage(String resolvedMessage) {
        this.tokens = newArrayList();
        parse(resolvedMessage);
    }


    private void parse(String resolvedMessage) {
        Matcher matcher = TOKEN_PATTERN.matcher(resolvedMessage);
        while (matcher.find()) {
            tokens.add(new Token(matcher.group()));
        }
    }

    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }
}
