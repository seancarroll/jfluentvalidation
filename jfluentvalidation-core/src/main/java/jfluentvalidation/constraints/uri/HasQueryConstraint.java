package jfluentvalidation.constraints.uri;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

public class HasQueryConstraint<T> implements Constraint<T, URI> {

    private final String expected;

    public HasQueryConstraint(String expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        return context.getPropertyValue().getQuery().equals(expected);
    }
}
