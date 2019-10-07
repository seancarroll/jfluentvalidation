package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.regex.Pattern;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation

/**
 * Check that the given {@code CharSequence} being validated matches the given regular expression.
 *
 * @param <T>  type of instance to validate.
 */
public class MatchesConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final Pattern pattern;

    public MatchesConstraint(CharSequence regex) {
        this(Pattern.compile(Ensure.notNull(regex.toString())));
    }

    public MatchesConstraint(Pattern pattern) {
        super(DefaultMessages.CHARSEQUENCE_MATCHES);
        this.pattern = Ensure.notNull(pattern);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return pattern.matcher(context.getPropertyValue()).matches();
    }

    @Override
    public void addParametersToContext(RuleContext<T, A> context) {
        context.getMessageContext().appendArgument("pattern", pattern);
    }
}
