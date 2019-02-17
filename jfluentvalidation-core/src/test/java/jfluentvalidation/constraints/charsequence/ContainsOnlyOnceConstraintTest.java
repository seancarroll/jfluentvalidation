package jfluentvalidation.constraints.charsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainsOnlyOnceConstraintTest {

    // TODO: better test method names
    @Test
    void shouldReturnTrueWhenActualContainsGivenStringOnlyOnce() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("red");
        assertTrue(constraint.isValid("credit"));
    }

    @Test
    void shouldReturnFalseWhenActualContainsGivenStringMoreThanOnce() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("red");
        assertFalse(constraint.isValid("creditcredit"));
    }

    @Test
    void shouldReturnFalseWhenActualContainsSequenceOnlyOnceButDifferentCase() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("Red");
        assertFalse(constraint.isValid("credit"));
    }

    @Test
    void shouldReturnFalseWhenActualDoesNotContainString() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("blue");
        assertFalse(constraint.isValid("credit"));
    }

    @Test
    void shouldThrowIfSequenceIsNull() {
        assertThrows(NullPointerException.class, () -> new ContainsOnlyOnceConstraint(null));
    }

    @Test
    void shouldReturnFalseWhenActualIsNull() {
        ContainsOnlyOnceConstraint constraint = new ContainsOnlyOnceConstraint("red");
        assertFalse(constraint.isValid(null));
    }
}
