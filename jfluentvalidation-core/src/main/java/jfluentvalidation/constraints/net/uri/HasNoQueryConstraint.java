package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URI;

public class HasNoQueryConstraint<T> extends AbstractConstraint<T, URI> {

    public HasNoQueryConstraint() {
        super(DefaultMessages.NET_HAS_NO_QUERY);
    }

    @Override
    public boolean isValid(RuleContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getQuery() == null;
    }

}
