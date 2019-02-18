package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.util.regex.Pattern;


// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation

/**
 * Checks that the given {@code CharSequence} being validated contains the given regular expression or {@code Patter}.
 */
public class ContainsPatternConstraint implements Constraint<CharSequence> {

    private final Pattern pattern;

    public ContainsPatternConstraint(CharSequence regex) {
        this(Pattern.compile(Ensure.notNull(regex).toString()));
    }

    public ContainsPatternConstraint(Pattern pattern) {
        this.pattern = Ensure.notNull(pattern);
    }

    @Override
    public boolean isValid(CharSequence value) {
        return pattern.matcher(value).find();
    }
}
