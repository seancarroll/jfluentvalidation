package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Collection;
import java.util.function.Predicate;

// QUESTION: how is this different than SoftConstraint? Should this use it?
// This looks like a more general case of the ContainsAllIn/ContainsAnyIn/ContainsExactlyElements/ContainsNoneIn/etc
public class ItemConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Predicate<? super P> condition;
    // TODO: should this just be a single constraint?
    private final Constraint<T, ? super P>[] innerConstraints;

    public ItemConstraint(Constraint<T, P>[] innerConstraints) {
        this(null, innerConstraints);
    }

    public ItemConstraint(Predicate<? super P> condition, Constraint<T, ? super P>[] innerConstraints) {
        super(DefaultMessages.ITERABLE_ITEM);
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        // TODO: fix...need to collect results and return
        // TODO: does it make sense to move this out somewhere common?
        boolean isValid = true;
        Collection<P> actual = (Collection<P>) Iterables.toCollection(context.getPropertyValue());
        for (int i = 0; i < innerConstraints.length; i++) {
            for (P item : actual) {
                // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
                if (condition == null || condition.test(item)) {
                    // TODO: better way to do this? not sure this is even correct when using the RuleContext
                    ValidationContext childContext = new ValidationContext(item);
                    if (!innerConstraints[i].isValid(new RuleContext(childContext, context.getRule(), item))) {
                        isValid = false;
                        context.appendArgument("index", i);
                        context.appendArgument("PropertyName", context.getRule().getPropertyName());
                        context.appendArgument("PropertyValue", context.getPropertyValue());
                    }
                }
            }
        }

        return isValid;
    }
}
