package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

// TODO: Could I do something like the following?
// Seems like it will work. Whats the cost of the cast?
public class ArrayLengthConstraint<T, A> extends AbstractConstraint<T, A> {

    private IntSupplier minSupplier;
    private IntSupplier maxSupplier;

    public ArrayLengthConstraint(IntSupplier minSupplier, IntSupplier maxSupplier) {
        super(DefaultMessages.ARRAY_LENGTH);
        this.minSupplier = minSupplier;
        this.maxSupplier = maxSupplier;
    }

    public ArrayLengthConstraint(int min, int max) {
        super(DefaultMessages.ARRAY_LENGTH);
        this.minSupplier = () -> min;
        this.maxSupplier = () -> max;
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        int len = Array.getLength(context.getPropertyValue());
        int min = minSupplier.getAsInt();
        int max = maxSupplier.getAsInt();
        if (len < min || (len > max && max != -1)) {
            return false;
        }

        return true;
    }


}

