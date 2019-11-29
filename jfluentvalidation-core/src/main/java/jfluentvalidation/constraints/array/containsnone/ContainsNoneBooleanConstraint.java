package jfluentvalidation.constraints.array.containsnone;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import static com.google.common.primitives.Booleans.asList;

public class ContainsNoneBooleanConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final Collection<Boolean> excluded;

    public ContainsNoneBooleanConstraint(Iterable<Boolean> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE_IN);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Boolean> actual = asList(context.getPropertyValue());
        Collection<Boolean> present = new ArrayList<>();
        for (boolean item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(RuleContext<T, boolean[]> context) {
        context.getMessageContext().appendArgument("values", excluded);
    }
}
