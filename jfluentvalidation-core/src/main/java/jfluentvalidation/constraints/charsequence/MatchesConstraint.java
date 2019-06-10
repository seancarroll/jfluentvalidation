package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.regex.Pattern;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation

/**
 * Check that the given {@code CharSequence} being validated matches the given regular expression.
 *
 * @param <T>  type of instance to validate.
 */
public class MatchesConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final Pattern pattern;

    public MatchesConstraint(CharSequence regex) {
        this(Pattern.compile(regex.toString()));
    }

    public MatchesConstraint(Pattern pattern) {
        super(DefaultMessages.CHARSEQUENCE_MATCHES);
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return pattern.matcher(validationContext.getPropertyValue()).matches();
    }
}
