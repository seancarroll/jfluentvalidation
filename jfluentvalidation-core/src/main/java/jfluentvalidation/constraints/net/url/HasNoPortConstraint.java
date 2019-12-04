package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

public class HasNoPortConstraint<T> extends AbstractConstraint<T, URL> {

    public HasNoPortConstraint() {
        super(DefaultMessages.NET_HAS_NO_PORT);
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getPort() == -1;
    }

}
