package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.regex.Pattern;

/**
 * Check that the given {@code CharSequence} being validated does not match the given regular expression.
 *
 * @param <T>  type of instance to validate.
 */
public class DoesNotMatchConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final Pattern pattern;

    public DoesNotMatchConstraint(CharSequence regex) {
        this(Pattern.compile(Ensure.notNull(regex.toString())));
    }

    public DoesNotMatchConstraint(Pattern pattern) {
        super(DefaultMessages.CHARSEQUENCE_DOES_NOT_MATCH);
        this.pattern = Ensure.notNull(pattern);
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !pattern.matcher(context.getPropertyValue()).matches();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("pattern", pattern);
    }
}
