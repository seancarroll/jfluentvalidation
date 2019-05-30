package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainsOnlyOnceConstraintTest {

    // TODO: better test method names
    @Test
    void shouldReturnTrueWhenActualContainsGivenStringOnlyOnce() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("red");
        RuleContext context = new RuleContext(new ValidationContext("credit"), null, "red blue");
        assertTrue(constraint.isValid(context));
    }

    @Test
    void shouldReturnFalseWhenActualContainsGivenStringMoreThanOnce() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("red");
        RuleContext context = new RuleContext(new ValidationContext("credit"), null, "red blue red");
        assertFalse(constraint.isValid(context));
    }

    @Test
    void shouldReturnFalseWhenActualContainsSequenceOnlyOnceButDifferentCase() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("Red");
        RuleContext context = new RuleContext(new ValidationContext("credit"), null, "red");
        assertFalse(constraint.isValid(context));
    }

    @Test
    void shouldReturnFalseWhenActualDoesNotContainString() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("blue");
        RuleContext context = new RuleContext(new ValidationContext("credit"), null, "red");
        assertFalse(constraint.isValid(context));
    }

    @Test
    void shouldThrowIfSequenceIsNull() {
        assertThrows(NullPointerException.class, () -> new ContainsOnlyOnceConstraint(null));
    }

    @Test
    void shouldReturnFalseWhenActualIsNull() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("blue");
        RuleContext context = new RuleContext(new ValidationContext("credit"), null, null);
        assertFalse(constraint.isValid(context));
    }
}
