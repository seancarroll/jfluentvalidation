package jfluentvalidation.constraints.inputstream;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.io.InputStream;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class HasContentConstraint<T> implements Constraint<T, InputStream> {

    @Override
    public boolean isValid(RuleContext<T, InputStream> context) {
        return false;
    }

}
