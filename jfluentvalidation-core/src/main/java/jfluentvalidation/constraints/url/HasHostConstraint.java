package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

public class HasHostConstraint<T> implements Constraint<T, URL> {

    private final String expected;

    public HasHostConstraint(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        return context.getPropertyValue().getHost().equals(expected);
    }
}
