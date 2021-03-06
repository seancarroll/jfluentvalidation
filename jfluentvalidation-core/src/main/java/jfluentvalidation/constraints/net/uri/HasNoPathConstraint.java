package jfluentvalidation.constraints.net.uri;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URI;

public class HasNoPathConstraint<T> extends AbstractConstraint<T, URI> {

    public HasNoPathConstraint() {
        super(DefaultMessages.NET_HAS_NO_PATH);
    }

    @Override
    public boolean isValid(ConstraintContext<T, URI> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return "".equals(context.getPropertyValue().getPath());
    }

}

