package jfluentvalidation.constraints.array.containsnone;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import static com.google.common.primitives.Bytes.asList;


public class ContainsNoneByteConstraint<T> extends AbstractConstraint<T, byte[]> {

    private final Collection<Byte> excluded;

    public ContainsNoneByteConstraint(Iterable<Byte> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE_IN);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<Byte> actual = asList(context.getPropertyValue());
        Collection<Byte> present = new ArrayList<>();
        for (byte item : new LinkedHashSet<>(excluded)) {
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
    public void addParametersToContext(RuleContext<T, byte[]> context) {
        context.getMessageContext().appendArgument("values", excluded);
    }
}
