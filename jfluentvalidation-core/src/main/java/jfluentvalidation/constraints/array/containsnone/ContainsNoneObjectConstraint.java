package jfluentvalidation.constraints.array.containsnone;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ContainsNoneObjectConstraint<T, P> extends AbstractConstraint<T, P[]> {

    private final Collection<P> excluded;

    public ContainsNoneObjectConstraint(Iterable<P> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE_IN);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(RuleContext<T, P[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<P> actual = Arrays.stream(context.getPropertyValue()).collect(Collectors.toList());
        Collection<P> present = new ArrayList<>();
        for (P item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(RuleContext<T, P[]> context) {
        context.getMessageContext().appendArgument("values", excluded);
    }
}
