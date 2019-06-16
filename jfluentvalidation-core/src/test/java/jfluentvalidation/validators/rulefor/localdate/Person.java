package jfluentvalidation.validators.rulefor.localdate;

import java.time.LocalDate;

class Person {

    private LocalDate birthday;

    public Person(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
