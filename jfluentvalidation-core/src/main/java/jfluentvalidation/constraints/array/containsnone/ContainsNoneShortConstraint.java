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

public class ContainsNoneShortConstraint<T> extends AbstractConstraint<T, short[]> {

    private final Collection<Short> excluded;

    public ContainsNoneShortConstraint(Iterable<Short> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE_IN);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Short> actual = asList(context.getPropertyValue());
        Collection<Short> present = new ArrayList<>();
        for (short item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(RuleContext<T, short[]> context) {
        context.getMessageContext().appendArgument("values", excluded);
    }
}
