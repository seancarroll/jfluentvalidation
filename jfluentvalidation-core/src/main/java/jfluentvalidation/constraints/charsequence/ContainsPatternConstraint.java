package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.regex.Pattern;


// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation

/**
 * Checks that the given {@code CharSequence} being validated contains the given regular expression or {@code Patter}.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsPatternConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final Pattern pattern;

    // TODO: do we want to include offset?
    public ContainsPatternConstraint(CharSequence regex) {
        this(Pattern.compile(Ensure.notNull(regex).toString()));
    }

    public ContainsPatternConstraint(Pattern pattern) {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_PATTERN);
        this.pattern = Ensure.notNull(pattern);
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return pattern.matcher(context.getPropertyValue()).find();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("pattern", pattern);
    }
}
