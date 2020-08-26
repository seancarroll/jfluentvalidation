package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.net.URL;

public class HasNoPathConstraint<T> extends AbstractConstraint<T, URL> {

    public HasNoPathConstraint() {
        super(DefaultMessages.NET_HAS_NO_PATH);
    }

    @Override
    public boolean isValid(ConstraintContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return "".equals(context.getPropertyValue().getPath());
    }

}

