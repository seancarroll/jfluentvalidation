package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URL;

public class HasNoQueryConstraint<T> extends AbstractConstraint<T, URL> {

    public HasNoQueryConstraint() {
        super(DefaultMessages.NET_HAS_NO_QUERY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getQuery() == null;
    }

}
