package jfluentvalidation.validators.rulefor.localdatetime;

import java.time.LocalDateTime;

class Person {

    private LocalDateTime birthday;

    public Person(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }
}
