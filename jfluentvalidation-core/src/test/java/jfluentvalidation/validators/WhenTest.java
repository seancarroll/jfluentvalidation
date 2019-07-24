package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhenTest {

    @Test
    void when() {
        // RuleFor(customer => customer.Address.PostCode).NotNull().When(customer => customer.Address != null)
        Address address = new Address("street1", "city", "state", null);
        Person person = new Person("sean", 32, address);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(p -> p.getAddress().getZip()).isNotNull().when(p -> p.getAddress() != null);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
        assertEquals("zip", validationFailures.get(0).getPropertyName());
    }


}
