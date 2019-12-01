package jfluentvalidation.constraints.array.containsnone;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import static jfluentvalidation.common.Lists.asList;

public class ContainsNoneIntConstraint<T> extends AbstractConstraint<T, int[]> {

    private final Collection<Integer> excluded;

    public ContainsNoneIntConstraint(Iterable<Integer> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Integer> actual = asList(context.getPropertyValue());
        Collection<Integer> present = new ArrayList<>();
        for (int item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(RuleContext<T, int[]> context) {
        context.getMessageContext().appendArgument("excluded", excluded);
    }
}
