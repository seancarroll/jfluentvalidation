package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.regex.Pattern;

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
