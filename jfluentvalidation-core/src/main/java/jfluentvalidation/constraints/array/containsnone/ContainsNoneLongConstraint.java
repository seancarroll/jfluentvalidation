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

public class ContainsNoneLongConstraint<T> extends AbstractConstraint<T, long[]> {

    private final Collection<Long> excluded;

    public ContainsNoneLongConstraint(Iterable<Long> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(ConstraintContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Long> actual = asList(context.getPropertyValue());
        Collection<Long> present = new ArrayList<>();
        for (long item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(ConstraintContext<T, long[]> context) {
        context.getMessageContext().appendArgument("excluded", excluded);
    }
}
