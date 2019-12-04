package jfluentvalidation.constraints.net.url;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.net.URL;

public class HasNoAnchorConstraint<T> extends AbstractConstraint<T, URL> {

    public HasNoAnchorConstraint() {
        super(DefaultMessages.URL_HAS_NO_ANCHOR);
    }

    @Override
    public boolean isValid(RuleContext<T, URL> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().getRef() == null;
    }

}
