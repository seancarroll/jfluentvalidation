package jfluentvalidation.constraints.array;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.function.Predicate;

// QUESTION: how is this different than SoftConstraint? Should this use it?
// This looks like a more general case of the ContainsAllIn/ContainsAnyIn/ContainsExactlyElements/ContainsNoneIn/etc
public class ItemConstraint<T> extends AbstractConstraint<T, Object[]> {

    private final Predicate<Object> condition;
    // TODO: should this just be a single constraint?
    private final Constraint<T, Object>[] innerConstraints;

    public ItemConstraint(Constraint<T, Object>[] innerConstraints) {
        this(null, innerConstraints);
    }

    public ItemConstraint(Predicate<Object> condition, Constraint<T, Object>[] innerConstraints) {
        super(DefaultMessages.ITERABLE_ITEM);
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        // TODO: fix...need to collect results and return
        // TODO: does it make sense to move this out somewhere common?

        boolean encounteredFailure = false;
        for (Object item : context.getPropertyValue()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition == null || condition.test(item)) {
                for (Constraint c : innerConstraints) {
                    // TODO: better way to do this? not sure this is even correct when using the RuleContext
                    ValidationContext childContext = new ValidationContext(item);
                    return c.isValid(new RuleContext(childContext, context.getRule(), item));
                }
            }
        }

        return false;
    }
}
