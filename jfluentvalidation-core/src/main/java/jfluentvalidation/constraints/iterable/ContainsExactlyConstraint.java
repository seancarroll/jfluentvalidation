package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.Lists;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

// TODO: have a base class for array, iterable, map???
public class ContainsExactlyConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<P> expected;

    public ContainsExactlyConstraint(Iterable<P> expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // TODO: anyway to avoid this cast?
        Collection<P> actual = (Collection<P>) Iterables.toCollection(context.getPropertyValue());
        List<P> expectedAsList = Lists.newArrayList(expected);

        IterableDifference<P> diff = IterableDifference.diff(actual, expectedAsList);
        if (!diff.differencesFound()) {
            int i = 0;
            for (P elementFromActual : actual) {
                if (!Objects.equals(elementFromActual, expectedAsList.get(i))) {
                    context.getMessageContext().appendArgument("differentOrderElement", elementFromActual);
                    context.getMessageContext().appendArgument("indexOfDifferentElement", i);
                    context.getMessageContext().appendArgument("missingValues", null);
                    context.getMessageContext().appendArgument("unexpectedValues", null);
                    return false;
                }
                i++;
            }
            return true;
        }

        // need to set all values to avoid UnresolvablePropertyException when evaluating validation message
        context.getMessageContext().appendArgument("differentOrderElement", null);
        context.getMessageContext().appendArgument("indexOfDifferentElement", null);
        context.getMessageContext().appendArgument("missingValues", diff.getMissing().isEmpty() ? null : diff.getMissing());
        context.getMessageContext().appendArgument("unexpectedValues", diff.getUnexpected().isEmpty() ? null : diff.getUnexpected());


        return false;
    }
}
