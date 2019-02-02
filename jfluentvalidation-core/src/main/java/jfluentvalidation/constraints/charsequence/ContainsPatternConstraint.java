package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

import java.util.regex.Pattern;


// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation
public class ContainsPatternConstraint implements Constraint<CharSequence> {

    private final Pattern pattern;

    public ContainsPatternConstraint(CharSequence regex) {
        this(Pattern.compile(regex.toString()));
    }

    public ContainsPatternConstraint(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(CharSequence instance) {
//        checkRegexIsNotNull(regex);
//        assertNotNull(info, actual);
        return pattern.matcher(instance).find();
    }
}
