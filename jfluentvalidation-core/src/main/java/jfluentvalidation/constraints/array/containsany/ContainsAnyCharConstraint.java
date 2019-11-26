package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class ContainsAnyCharConstraint<T> extends AbstractConstraint<T, char[]> {

    private final Iterable<Character> values;

    public ContainsAnyCharConstraint(Iterable<Character> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Character> actual = asList(context.getPropertyValue());
        for (char item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    private static List<Character> asList(char[] chars) {
        final List<Character> list = new ArrayList<>(chars.length);
        for (char b : chars) {
            list.add(b);
        }
        return list;
    }
}
