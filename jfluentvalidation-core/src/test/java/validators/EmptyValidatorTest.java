package validators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyValidatorTest {


    @Test
    public void testPerson() {
        Person sean = new Person("Sean", 32, new Address("123 Some ST", "Chicago", "IL", "60606"));

        assertThat(sean.getName()).isEqualTo("Sean");
        // assertThat(sean).extracting("name").isEqualTo("Sean");
        assertThat(sean.getAge()).isEqualTo(32);
        // assertThat(sean.getAddress()).startsWith("123").contains("Some ST").doesNotContain("New York");
    }


}
