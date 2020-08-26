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


public class ContainsNoneCharConstraint<T> extends AbstractConstraint<T, char[]> {

    private final Collection<Character> excluded;

    public ContainsNoneCharConstraint(Iterable<Character> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(ConstraintContext<T, char[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Character> actual = asList(context.getPropertyValue());
        Collection<Character> present = new ArrayList<>();
        for (char item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(ConstraintContext<T, char[]> context) {
        context.getMessageContext().appendArgument("excluded", excluded);
    }
}
