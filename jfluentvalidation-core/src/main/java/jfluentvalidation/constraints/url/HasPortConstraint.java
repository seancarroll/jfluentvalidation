package jfluentvalidation.constraints.url;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

public class HasPortConstraint<T> implements Constraint<T, URL> {

    private final int expected;

    public HasPortConstraint(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        return context.getPropertyValue().getPort() == expected;
    }
}
