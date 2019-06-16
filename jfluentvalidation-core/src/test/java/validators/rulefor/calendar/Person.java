package validators.rulefor.calendar;

import java.util.Calendar;

class Person {

    private Calendar date;

    public Person(Calendar date) {
        this.date = date;

    }

    public Calendar getBirthday() {
        return date;
    }
}
