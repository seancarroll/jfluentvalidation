package jfluentvalidation.constraints.array.containsnone;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import static jfluentvalidation.common.Lists.asList;

public class ContainsNoneDoubleConstraint<T> extends AbstractConstraint<T, double[]> {

    private final Collection<Double> excluded;

    public ContainsNoneDoubleConstraint(Iterable<Double> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(ConstraintContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Double> actual = asList(context.getPropertyValue());
        Collection<Double> present = new ArrayList<>();
        for (double item : new LinkedHashSet<>(excluded)) {
            if (actual.contains(item)) {
                present.add(item);
            }
        }

        if (!present.isEmpty()) {
            // TODO: do I want to use this in the message?
            context.getMessageContext().appendArgument("present", present);
            return false;
        }

        return true;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, double[]> context) {
        context.getMessageContext().appendArgument("excluded", excluded);
    }
}
