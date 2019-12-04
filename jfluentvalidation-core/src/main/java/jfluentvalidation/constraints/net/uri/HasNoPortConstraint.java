package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

public class HasNoPortConstraint<T> extends AbstractConstraint<T, URI> {

    public HasNoPortConstraint() {
        super(DefaultMessages.NET_HAS_NO_PORT);
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getPort() == -1;
    }

}
